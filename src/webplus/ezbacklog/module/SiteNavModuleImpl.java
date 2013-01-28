package webplus.ezbacklog.module;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.interfaces.SiteNavModule;

import com.google.appengine.api.users.UserService;

public class SiteNavModuleImpl implements SiteNavModule {

	@Autowired private UserService userService;
	private SiteNav sitenav;
	
	public void init() {
		sitenav = new SiteNav();
		sitenav.setUserName(userService.getCurrentUser().getNickname());
	}
	
	@Override
	public SiteNav getSiteNav() {
		return sitenav;
	}

}
