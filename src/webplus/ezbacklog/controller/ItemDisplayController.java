package webplus.ezbacklog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import webplus.ezbacklog.model.Backlogger;
import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.model.ItemAggregation;
import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.interfaces.BackloggerModule;
import webplus.ezbacklog.module.interfaces.DashboardModule;
import webplus.ezbacklog.module.interfaces.ItemDisplayModule;
import webplus.ezbacklog.module.interfaces.SiteNavModule;

import com.google.gson.Gson;

/**
 * This controller handles all full page load request for getting item.
 */
@Controller
public class ItemDisplayController {
	@Autowired
	private Gson gson;
	@Autowired
	private SiteNavModule sitenavModule;
	@Autowired
	private ItemDisplayModule itemDisplayModule;
	@Autowired
	private DashboardModule dashboardModule;
	@Autowired
	private BackloggerModule backloggerModule;

	@RequestMapping("/f/items")
	public String loadPrject(Model model) {
		return load(-1L, model);
	}

	@RequestMapping("/f/items/{id}/{defaultSelectId}")
	public String load(@PathVariable("id") Long id, @PathVariable("defaultSelectId") Long defaultSelectId, Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setProject(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		ItemAggregation itemAggregation = dashboardModule.getItemAggregationByParentId(id);
		model.addAttribute(ItemAggregation.MODEL, gson.toJson(itemAggregation));
		model.addAttribute(Backlogger.MODEL, backloggerModule.getCurrencyBacklogger());
		List<Item> items = itemDisplayModule.getItemByParentId(id);
		model.addAttribute("Items", gson.toJson(items));
		model.addAttribute("DefaultSelectId", defaultSelectId);
		return "item";
	}

	@RequestMapping("/f/items/{id}")
	public String load(@PathVariable("id") Long id, Model model) {
		return load(id, null, model);
	}
}
