package webplus.ezbacklog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.SiteNavModule;

import com.google.gson.Gson;

@Controller
public class TeamDisplayController {
	@Autowired
	private SiteNavModule sitenavModule;
	@Autowired
	private Gson gson;
	@Autowired
	private BackloggerModule backloggerModule;

	@RequestMapping(value = { "/f/team" }, method = RequestMethod.GET)
	public String displayTeamPage(Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setTeam(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		return "team";
	}
}
