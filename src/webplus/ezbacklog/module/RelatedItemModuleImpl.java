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
import webplus.ezbacklog.model.RelatedItem;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.RelatedItemModule;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.service.UrlNormalizer;

public class RelatedItemModuleImpl implements RelatedItemModule {

	@Autowired
	private BackloggerModule backloggerModule;

	@Autowired
	private UrlNormalizer urlNormalizer;

	@Override
	public List<RelatedItem> fecthRelatedItems(Long itemId) {
		if (itemId == null || itemId <= 0) {
			return null;
		}
		String filter = "itemId == " + itemId;
		return query(filter);
	}

	@Override
	public void addRelatedItem(RelatedItem relatedItem) {
		validate(relatedItem);
		relatedItem.setId(null);
		relatedItem.setCreateDate(Calendar.getInstance().getTime());
		relatedItem.setCreatorEmail(backloggerModule.getCurrencyBacklogger().getEmail());
		relatedItem.setRelatedItem(urlNormalizer.normalize(relatedItem.getRelatedItem()));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(relatedItem);
		} catch (Exception e) {
			throw new DBException(e);
		} finally {
			pm.close();
		}
	}

	private void validate(RelatedItem relatedItem) {
		try {
			checkNotNull(relatedItem.getRelatedItem());
			checkNotNull(relatedItem.getItemId());
			checkArgument(relatedItem.getItemId() > 0, "The item id is invalid");
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private List<RelatedItem> query(String filter) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(RelatedItem.class);
		query.setFilter(filter);
		return (List<RelatedItem>) query.execute();
	}

	@Override
	public RelatedItem deleteRelatedItem(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		RelatedItem relatedItem = null;
		try {
			pm.deletePersistent(pm.getObjectById(RelatedItem.class, id));
			return relatedItem;
		} catch (JDOObjectNotFoundException e) {
			return null;
		} finally {
			pm.close();
		}
	}
}
