package webplus.ezbacklog.module;

import static webplus.ezbacklog.values.ItemLevel.PROJECT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.service.PMF;

public class ItemDisplayModuleImpl implements ItemDisplayModule {

	@Autowired
	private BackloggerModule backloggerModule;
	private Map<Long, List<Item>> getItemByParentIdResultCache;

	public ItemDisplayModuleImpl() {
		getItemByParentIdResultCache = new HashMap<Long, List<Item>>();
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
			if (parentId <= 0) {
				filter = "ownerEmail == '%s' && itemLevel == " + PROJECT;
			} else {
				filter = "ownerEmail == '%s' && parentId == %d";
			}
			filter = String.format(filter, backloggerModule.getCurrencyBacklogger().getEmail(), parentId);
			List<Item> items = query(filter);
			getItemByParentIdResultCache.put(parentId, items);
			return items;
		}
	}

	@SuppressWarnings("unchecked")
	private List<Item> query(String filter) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Item.class);
		query.setFilter(filter);
		return (List<Item>) query.execute();
	}
}
