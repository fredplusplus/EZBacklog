package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkNotNull;
import static webplus.ezbacklog.values.ActivityType.Update;

import java.util.Calendar;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.exceptions.DBException;
import webplus.ezbacklog.model.Activity;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.module.interfaces.ActivityUpdateModule;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.values.ActivityType;

public class ActivityUpdateModuleImpl implements ActivityUpdateModule {

	@Autowired
	private BackloggerModule backloggerModule;

	@Override
	public void addCreationAcitivity(Item item) {
		Activity act = new Activity();
		act.setActivityType(ActivityType.Create);
		act.setItemId(item.getId());
		saveActivity(act);
	}

	@Override
	public void addUpdateActivity(Item item) {
		Activity act = new Activity();
		act.setActivityType(Update);
		act.setItemId(item.getId());
		checkNotNull(act.getItemId());
		act.setResolvedPoint(item.getResolvedPoint());
		saveActivity(act);
	}

	private void saveActivity(Activity act) {
		act.setUserEmail(backloggerModule.getCurrencyBacklogger().getEmail());
		act.setTime(Calendar.getInstance().getTime());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(act);
		} catch (Exception e) {
			throw new DBException(e);
		} finally {
			pm.close();
		}
	}
}
