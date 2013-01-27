package webplus.ezbacklog.module;

import java.util.Calendar;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Backlogger;
import webplus.ezbacklog.service.PMF;

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
		try {
			backlogger = pm.detachCopy(pm.getObjectById(Backlogger.class, email));
		} catch (JDOObjectNotFoundException e) {
			backlogger = new Backlogger();
			backlogger.setEmail(email);
			backlogger.setJoinDate(Calendar.getInstance().getTime());
			backlogger.setShowActive(true);
			backlogger.setShowDeleted(false);
			backlogger.setShowResolved(true);
			pm.makePersistent(backlogger);
		} finally {
			pm.close();
		}
	}

	@Override
	public void updatePreference() {
		// TODO Auto-generated method stub
		
	}

}
