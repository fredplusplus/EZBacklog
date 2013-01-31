package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkNotNull;
import static webplus.ezbacklog.values.ActivityType.Burndown;
import static webplus.ezbacklog.values.ActivityType.Update;

import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.exceptions.DBException;
import webplus.ezbacklog.model.Activity;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.module.interfaces.ActivityUpdateModule;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.service.StringNormalizer;
import webplus.ezbacklog.values.ActivityType;
import webplus.ezbacklog.values.ItemStatus;

public class ActivityUpdateModuleImpl implements ActivityUpdateModule {

	@Autowired
	private BackloggerModule backloggerModule;
	@Autowired
	private StringNormalizer stringNormalizer;

	@Override
	public void addCreationAcitivity(Item item) {
		Activity act = new Activity();
		act.setActivityType(ActivityType.Create);
		act.setItemId(item.getId());
		saveActivity(act);
	}

	@Override
	public void addUpdateActivity(Item oldItem, Item newItem) {
		Activity act = new Activity();
		act.setItemId(newItem.getId());
		checkNotNull(act.getItemId());
		act.setResolvedPoint(newItem.getResolvedPoint());
		act.setActivityType(Update);
		if (oldItem.getStatus().equals(ItemStatus.Open) && newItem.getStatus().equals(ItemStatus.Resolved)) {
			act.setActivityType(ActivityType.Resolve);
		} else if (newItem.getStatus().equals(ItemStatus.Open) && oldItem.getStatus().equals(ItemStatus.Resolved)) {
			act.setActivityType(ActivityType.Reopen);
		} else if (newItem.getStatus().equals(ItemStatus.Open) && oldItem.getStatus().equals(ItemStatus.Open)) {
			if (oldItem.getResolvedPoint() != newItem.getResolvedPoint()) {
				act.setActivityType(Burndown);
			}
		}
		saveActivity(act);
	}

	@Override
	public void addDeleteActivity(Item item) {
		Activity act = new Activity();
		act.setActivityType(ActivityType.Delete);
		act.setItemId(item.getId());
		checkNotNull(act.getItemId());
		act.setResolvedPoint(item.getResolvedPoint());
		saveActivity(act);
	}

	public void saveActivity(Activity act) {
		act.setUserEmail(backloggerModule.getCurrencyBacklogger().getEmail());
		act.setTime(Calendar.getInstance().getTime());
		act.setDescription(stringNormalizer.normalize(act.getDescription()));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(act);
		} catch (Exception e) {
			throw new DBException(e);
		} finally {
			pm.close();
		}
	}

	@Override
	public List<Activity> getAuditTrails(Long itemId) {
		if (itemId == null || itemId <= 0) {
			return null;
		}
		String filter = String.format("itemId == %d", itemId);
		return query(filter);
	}

	@SuppressWarnings("unchecked")
	private List<Activity> query(String filter) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Activity.class);
		query.setFilter(filter);
		query.setOrdering("time desc");
		return (List<Activity>) query.execute();
	}
}
