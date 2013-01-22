package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Calendar;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.exceptions.DBException;
import webplus.ezbacklog.exceptions.ValidationException;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.values.ItemLevel;

public class ItemUpdateModuleImpl implements ItemUpdateModule {

	@Autowired
	private BackloggerModule backloggerModule;

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
	public List<Item> getItemByLevel(Long level, Long parentId) {
		String filter = null;
		if (level == ItemLevel.PROJECT) {
			filter = "ownerEmail == '%s'";
		} else {
			filter = "ownerEmail == '%s' && parentId == %ld";
		}
		filter = String.format(filter, backloggerModule.getCurrencyBacklogger().getEmail(), parentId);
		return query(filter);
	}

	@SuppressWarnings("unchecked")
	private List<Item> query(String filter) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Item.class);
		query.setFilter(filter);
		return (List<Item>) query.execute();
	}

	@Override
	public void saveItem(Item item) {
		validate(item);
		item.setModifyDate(Calendar.getInstance().getTime());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(item);
		} catch (Exception e) {
			throw new DBException(e);
		} finally {
			pm.close();
		}
	}

	private void validate(Item item) {
		try {
			String ownerEmail = backloggerModule.getCurrencyBacklogger().getEmail();
			checkNotNull(item);
			checkNotNull(item.getOwnerEmail());
			checkArgument(item.getOwnerEmail().equals(ownerEmail),
					"This item owner is %s, it is not owned by current user %s.", item.getOwnerEmail(),
					item.getOwnerEmail());
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}
}
