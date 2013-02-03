package webplus.ezbacklog.module;

import org.springframework.beans.factory.annotation.Autowired;

import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.interfaces.SiteNavModule;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class SiteNavModuleImpl implements SiteNavModule {

	@Autowired
	private UserService userService;
	private SiteNav sitenav;

	public void init() {
		sitenav = new SiteNav();
		User user = userService.getCurrentUser();
		if (user != null) {
			sitenav.setUserName(user.getNickname());
			sitenav.setAuthenticated(true);
		} else {
			sitenav.setAuthenticated(false);
			sitenav.setLoginUrl(userService.createLoginURL("/f/dashboard"));
		}
	}

	@Override
	public SiteNav getSiteNav() {
		return sitenav;
	}

}
