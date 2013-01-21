package webplus.ezbacklog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.model.ItemAggregation;
import webplus.ezbacklog.model.SiteNav;
import webplus.ezbacklog.module.DashboardModule;
import webplus.ezbacklog.module.ItemUpdateModule;
import webplus.ezbacklog.module.SiteNavModule;
import webplus.ezbacklog.values.ItemLevel;
import webplus.ezbacklog.values.ItemStatus;

import com.google.gson.Gson;

@Controller
public class ItemController {
	private static final String JSON_MODEL = "ez_json";
	@Autowired
	private Gson gson;
	@Autowired
	private SiteNavModule sitenavModule;
	@Autowired
	private ItemUpdateModule itemUpdateModule;
	@Autowired
	private DashboardModule dashboardModule;

	@RequestMapping("/f/items")
	public String loadPage(Model model) {
		SiteNav sitenav = sitenavModule.getSiteNav();
		sitenav.setProject(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		ItemAggregation itemAggregation = dashboardModule.getItemAggregation(ItemLevel.PROJECT);
		model.addAttribute(ItemAggregation.MODEL, gson.toJson(itemAggregation));

		List<Item> items = itemUpdateModule.getItemByLevel(ItemLevel.PROJECT, null);
		model.addAttribute("Items", gson.toJson(items));

		return "item";
	}

	@RequestMapping(value = "/f/item", method = RequestMethod.POST)
	public String createItem(@RequestBody String itemString, Model model) {
		Item item = gson.fromJson(itemString, Item.class);
		model.addAttribute(JSON_MODEL, itemString);
		return "json";
	}

	@RequestMapping(value = "/f/item/{id}", method = RequestMethod.PUT)
	public String updateItem(@PathVariable("id") String id, @RequestBody String itemString, Model model) {
		model.addAttribute(JSON_MODEL, itemString);
		Item item = gson.fromJson(itemString, Item.class);
		return "json";
	}

	@RequestMapping(value = "/f/item/{id}", method = RequestMethod.DELETE)
	public String deleteItem(@PathVariable("id") Long id, Model model) {
		Item item = itemUpdateModule.getItemById(id);
		item.setStatus(ItemStatus.Deleted);
		// itemUpdateModule.saveItem(item);
		model.addAttribute(JSON_MODEL, gson.toJson(item));
		return "json";
	}
}
