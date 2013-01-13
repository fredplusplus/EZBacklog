package webplus.ezbacklog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.SiteNavModule;

import com.google.gson.Gson;

@Controller
public class AboutController {

	@Autowired private Gson gson;
	@Autowired private SiteNavModule sitenavModule;

	@RequestMapping("/f/about")
	public String execute(Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setAbout(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		return "about";
	}
}
