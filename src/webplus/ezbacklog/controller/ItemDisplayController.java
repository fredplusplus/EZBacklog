package webplus.ezbacklog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.model.ItemAggregation;
import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.DashboardModule;
import webplus.ezbacklog.module.ItemDisplayModule;
import webplus.ezbacklog.module.SiteNavModule;

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

	@RequestMapping("/f/items")
	public String loadPage(Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setProject(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		ItemAggregation itemAggregation = dashboardModule.getItemAggregationByParentId(-1);
		model.addAttribute(ItemAggregation.MODEL, gson.toJson(itemAggregation));

		List<Item> items = itemDisplayModule.getItemByParentId(-1);
		model.addAttribute("Items", gson.toJson(items));

		return "item";
	}
}
