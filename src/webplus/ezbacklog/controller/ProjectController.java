package webplus.ezbacklog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webplus.ezbacklog.model.ItemAggregation;
import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.DashboardModule;
import webplus.ezbacklog.module.ItemUpdateModule;
import webplus.ezbacklog.module.SiteNavModule;
import webplus.ezbacklog.values.ItemLevel;

import com.google.gson.Gson;

@Controller
public class ProjectController {

	@Autowired
	private Gson gson;
	@Autowired
	private SiteNavModule sitenavModule;
	@Autowired
	private ItemUpdateModule itemUpdateModule;
	@Autowired
	private DashboardModule dashboardModule;

	@RequestMapping("/f/projects")
	public String execute(Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setProject(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));

		ItemAggregation itemAggregation = dashboardModule.getItemAggregation(ItemLevel.PROJECT);
		model.addAttribute(ItemAggregation.MODEL, gson.toJson(itemAggregation));

		return "project";
	}
}
