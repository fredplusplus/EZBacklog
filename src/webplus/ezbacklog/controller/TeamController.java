package webplus.ezbacklog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.model.Team;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.SiteNavModule;
import webplus.ezbacklog.module.interfaces.TeamModule;

import com.google.gson.Gson;

@Controller
public class TeamController {
	@Autowired
	private SiteNavModule sitenavModule;
	@Autowired
	private Gson gson;
	@Autowired
	private BackloggerModule backloggerModule;
	@Autowired
	private TeamModule teamModule;

	private static final String TEAMS = "teams";

	@RequestMapping(value = { "/f/team" }, method = RequestMethod.GET)
	public String displayTeamPage(Model model) {
		List<Team> teams = teamModule.getAllTeamsForCurrentUser();
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setTeam(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		model.addAttribute(TEAMS, gson.toJson(teams));
		return "team";
	}
}
