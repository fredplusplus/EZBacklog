package webplus.ezbacklog.module;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.values.ItemLevel;

public class ItemUpdateModuleImpl implements ItemUpdateModule {

	@Autowired private BackloggerModule backloggerModule;
	
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
}
