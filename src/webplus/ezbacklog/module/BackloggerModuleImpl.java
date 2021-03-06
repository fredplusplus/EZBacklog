package webplus.ezbacklog.module;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Calendar;
import java.util.Locale;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Backlogger;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.service.PMF;
import webplus.ezbacklog.values.ItemLevel;

import com.google.appengine.api.users.UserService;

public class BackloggerModuleImpl implements BackloggerModule {

	@Autowired
	private UserService userService;

	private Backlogger backlogger;

	@Override
	public Backlogger getCurrencyBacklogger() {
		return backlogger;
	}

	@Override
	public void registerBacklogger() {
		String email = userService.getCurrentUser().getEmail();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		boolean needCreateNewUser = false;
		try {
			backlogger = pm.detachCopy(pm.getObjectById(Backlogger.class, email));
			if (backlogger.getDisplayLocale() == null) {
				backlogger.setDisplayLocale(Locale.ENGLISH);
			}
			if (backlogger.getOverviewlevel() == null) {
				backlogger.setOverviewlevel(ItemLevel.PROJECT);
			}
		} catch (JDOObjectNotFoundException e) {
			needCreateNewUser = true;
		}
		if (needCreateNewUser) {
			backlogger = new Backlogger();
			backlogger.setEmail(email);
			backlogger.setJoinDate(Calendar.getInstance().getTime());
			backlogger.setShowActive(true);
			backlogger.setShowResolved(true);
			backlogger.setShowDeleted(false);
			backlogger.setUsePoint(false);
			backlogger.setDisplayLocale(Locale.ENGLISH);
			backlogger.setOverviewlevel(1L);
			saveBacklogger(backlogger);
		}
	}

	@Override
	public void updatePreference(Backlogger prefs) {
		validatePrefs(prefs);
		backlogger.setShowActive(prefs.getShowActive());
		backlogger.setShowResolved(prefs.getShowResolved());
		backlogger.setShowDeleted(prefs.getShowDeleted());
		backlogger.setDisplayLocale(prefs.getDisplayLocale());
		backlogger.setOverviewlevel(prefs.getOverviewlevel());
		backlogger.setUsePoint(prefs.isUsePoint());
		saveBacklogger(backlogger);
	}

	private void validatePrefs(Backlogger prefs) {
		checkArgument(backlogger.getEmail().equalsIgnoreCase(prefs.getEmail()));
	}

	private void saveBacklogger(Backlogger backlogger) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(backlogger);
		} finally {
			pm.close();
		}
	}

	@Override
	public void registerBotBacklogger() {
		backlogger = new Backlogger();
		backlogger.setDisplayLocale(Locale.ENGLISH);
		backlogger.setEmail("user@user.com");
		backlogger.setJoinDate(Calendar.getInstance().getTime());
		backlogger.setOverviewlevel(2L);
		backlogger.setShowActive(true);
		backlogger.setShowDeleted(true);
		backlogger.setShowResolved(true);
		backlogger.setUsePoint(true);
	}

}
