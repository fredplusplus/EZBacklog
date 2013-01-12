package webplus.ezbacklog.module;

import java.util.Calendar;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Backlogger;
import webplus.ezbacklog.model.Dashboard;

import com.google.appengine.api.users.UserService;

public class DashboardModuleImpl implements DashboardModule {
	@Autowired
	private PersistenceManagerFactory pmf;
	@Autowired
	private UserService userService;

	@Override
	public Dashboard getDashboard() {
		Dashboard dashboard = new Dashboard();
		dashboard.setBacklogger(getCurrencyBacklogger());
		return dashboard;
	}

	private Backlogger getCurrencyBacklogger() {
		String email = userService.getCurrentUser().getEmail();
		Backlogger backlogger = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		try {
			backlogger = pm.detachCopy(pm.getObjectById(Backlogger.class, email));
		} catch (JDOObjectNotFoundException e) {
			backlogger = new Backlogger();
			backlogger.setEmail(email);
			backlogger.setJoinDate(Calendar.getInstance().getTime());
			pm.makePersistent(backlogger);
		} finally {
			pm.close();
		}
		return backlogger;
	}
}
