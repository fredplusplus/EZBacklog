package webplus.ezbacklog.module;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.Backlogger;
import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.SiteNavModule;

public class SiteNavModuleImpl implements SiteNavModule {

	@Autowired
	private BackloggerModule backloggerModule;
	private SiteNav sitenav;

	public void init() {
		sitenav = new SiteNav();
		Backlogger backlogger = backloggerModule.getCurrencyBacklogger();
		if (backlogger != null) {
			sitenav.setUserName(backlogger.getEmail());
			sitenav.setAuthenticated(true);
		} else {
			sitenav.setAuthenticated(false);
		}
	}

	@Override
	public SiteNav getSiteNav() {
		return sitenav;
	}

}
