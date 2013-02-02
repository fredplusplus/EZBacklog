package webplus.ezbacklog.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Backlogger;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.ItemDisplayModule;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.values.ItemLevel;
import webplus.ezbacklog.values.ItemStatus;

public class ItemDisplayModuleImpl implements ItemDisplayModule {

	@Autowired
	private BackloggerModule backloggerModule;
	private Map<Long, List<Item>> getItemByParentIdResultCache;
	private Map<Long, List<Item>> getItemByLevelResultCache;

	public ItemDisplayModuleImpl() {
		getItemByParentIdResultCache = new HashMap<Long, List<Item>>();
		getItemByLevelResultCache = new HashMap<Long, List<Item>>();
	}

	@Override
	public List<Item> getItemByLevel(long level) {
		if (getItemByLevelResultCache.get(level) != null) {
			return getItemByLevelResultCache.get(level);
		} else {
			String filter = "ownerEmail == '%s' && itemLevel == " + level;
			filter = String.format(filter, backloggerModule.getCurrencyBacklogger().getEmail());
			List<Item> items = query(filter);
			getItemByLevelResultCache.put(level, items);
			return items;
		}
	}

	@Override
	public Item getItemById(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			return pm.detachCopy(pm.getObjectById(Item.class, id));
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}
	}

	@Override
	public List<Item> getItemByParentId(long parentId) {
		if (getItemByParentIdResultCache.get(parentId) != null) {
			return getItemByParentIdResultCache.get(parentId);
		} else {
			String filter = null;
			List<Item> items = null;
			if (parentId <= 0) {
				filter = "ownerEmail == '%s' && itemLevel == " + ItemLevel.PROJECT;
			} else {
				filter = "ownerEmail == '%s' && parentId == %d";
			}
			String statusFilter = constructStatusFilter();
			if (statusFilter == null) {
				return items;
			} else if (statusFilter.length() > 0) {
				filter += " && " + statusFilter;
			}
			filter = String.format(filter, backloggerModule.getCurrencyBacklogger().getEmail(), parentId);
			items = query(filter);
			getItemByParentIdResultCache.put(parentId, items);
			return items;
		}
	}

	private String constructStatusFilter() {
		Backlogger backlogger = backloggerModule.getCurrencyBacklogger();
		String statusFilter = "";
		List<String> status = new ArrayList<String>();
		String format = "status == '%s'";
		if (backlogger.getShowActive()) {
			status.add(String.format(format, ItemStatus.Open.name()));
		}
		if (backlogger.getShowResolved()) {
			status.add(String.format(format, ItemStatus.Resolved.name()));
		}
		if (backlogger.getShowDeleted()) {
			status.add(String.format(format, ItemStatus.Deleted.name()));
		}
		if (status.size() > 0) {
			for (int i = 0; i < status.size(); i++) {
				if (i == 0) {
					statusFilter += status.get(i);
				} else {
					statusFilter += " || " + status.get(i);
				}
			}
			statusFilter = "(" + statusFilter + ")";
		} else {
			statusFilter = null;
		}

		return statusFilter;
	}

	@SuppressWarnings("unchecked")
	private List<Item> query(String filter) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Item.class);
		query.setFilter(filter);
		return (List<Item>) query.execute();
	}
}
