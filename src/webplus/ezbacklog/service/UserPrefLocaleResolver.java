package webplus.ezbacklog.service;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;

import webplus.ezbacklog.module.interfaces.BackloggerModule;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class UserPrefLocaleResolver implements LocaleResolver {

	@Autowired
	private BackloggerModule backloggerModule;

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null) {
			return Locale.ENGLISH;
		} else {
			return backloggerModule.getCurrencyBacklogger().getDisplayLocale();
		}

	}

	@Override
	public void setLocale(HttpServletRequest arg0, HttpServletResponse arg1, Locale arg2) {
		return;
	}

}
