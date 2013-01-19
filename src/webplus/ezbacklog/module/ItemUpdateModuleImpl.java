package webplus.ezbacklog.module;

import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.values.ItemLevel;
import webplus.ezbacklog.values.ItemStatus;

public class ItemUpdateModuleImpl implements ItemUpdateModule {

	@Autowired
	private BackloggerModule backloggerModule;

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getItemByLevel(Long level, Long parentId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query query = pm.newQuery(Item.class);
		String filter = null;
		if (level == ItemLevel.PROJECT) {
			filter = "ownerEmail == '%s'";
		} else {
			filter = "ownerEmail == '%s' && parentId == %ld";
		}
		query.setFilter(String.format(filter, backloggerModule.getCurrencyBacklogger().getEmail(), parentId));
		return (List<Item>) query.execute();
	}

	@Override
	public void updateItem(Item item) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			item.setCreationDate(Calendar.getInstance().getTime());
			item.setItemLevel(ItemLevel.PROJECT);
			item.setPoint(15L);
			item.setRank(29L);
			item.setOwnerEmail("test@example.com");
			item.setShortDescription("short blah");
			item.setLongDescription("This is a long description. Once upon a time, there is a long description.");
			item.setModifyDate(Calendar.getInstance().getTime());
			item.setStatus(ItemStatus.Open);
			pm.makePersistent(item);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}
}
