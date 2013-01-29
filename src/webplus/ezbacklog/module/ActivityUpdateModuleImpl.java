package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkNotNull;
import static webplus.ezbacklog.values.ActivityType.Update;

import java.util.Calendar;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;

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

	public void saveActivity(Activity act) {
		act.setUserEmail(backloggerModule.getCurrencyBacklogger().getEmail());
		act.setTime(Calendar.getInstance().getTime());
		if (act.getDescription() == null) {
			act.setDescription("");
		}
		act.setDescription(HtmlUtils.htmlUnescape(act.getDescription()));
		if (act.getDescription().length() > 2000) {
			act.setDescription(act.getDescription().substring(0, 1996) + "\n...");
		}
		act.setDescription(HtmlUtils.htmlEscape(act.getDescription()));
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
