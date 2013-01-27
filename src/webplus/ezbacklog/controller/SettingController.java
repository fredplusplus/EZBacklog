package webplus.ezbacklog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webplus.ezbacklog.model.Backlogger;
import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.BackloggerModule;
import webplus.ezbacklog.module.SiteNavModule;
import webplus.ezbacklog.values.Constants;

import com.google.gson.Gson;

/**
 * Display/perform setting action pages.
 */
@Controller
public class SettingController {

	@Autowired
	private SiteNavModule sitenavModule;
	@Autowired
	private Gson gson;
	@Autowired
	private BackloggerModule backloggerModule;

	@RequestMapping(value = { "/f/setting" }, method = RequestMethod.GET)
	public String execute(Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setSetting(true);
		Backlogger backlogger = backloggerModule.getCurrencyBacklogger();
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		model.addAttribute(Backlogger.MODEL, gson.toJson(backlogger));
		return "setting";
	}

	@RequestMapping(value = { "/f/setting" }, method = RequestMethod.POST)
	public String update(@RequestBody String prefJson, Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setSetting(true);
		Backlogger preference = gson.fromJson(prefJson, Backlogger.class);
		backloggerModule.updatePreference(preference);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		model.addAttribute(Constants.JSON_MODEL, gson.toJson(backloggerModule.getCurrencyBacklogger()));
		return "json";
	}
}
