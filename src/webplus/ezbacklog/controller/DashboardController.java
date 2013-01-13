package webplus.ezbacklog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webplus.ezbacklog.model.Dashboard;
import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.DashboardModule;
import webplus.ezbacklog.module.SiteNavModule;

import com.google.gson.Gson;

@Controller
public class DashboardController {

	@Autowired private Gson gson;
	@Autowired private DashboardModule dashboardModule;
	@Autowired private SiteNavModule sitenavModule;
	
	@RequestMapping({ "/f/dashboard", "/" })
	public String execute(Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setDashboard(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		model.addAttribute(Dashboard.MODEL, gson.toJson(dashboardModule.getDashboard()));
		return "dashboard";
	}
}
