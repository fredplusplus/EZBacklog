package webplus.ezbacklog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.SiteNavModule;

import com.google.gson.Gson;

@Controller
public class SettingController {

	@Autowired private SiteNavModule sitenavModule;
	@Autowired private Gson gson;
	
	@RequestMapping({ "/f/setting" })
	public String execute(Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setSetting(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		return "setting";
	}
}
