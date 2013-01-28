package webplus.ezbacklog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webplus.ezbacklog.model.Item;
import webplus.ezbacklog.module.interfaces.ActivityUpdateModule;
import webplus.ezbacklog.module.interfaces.ItemDisplayModule;
import webplus.ezbacklog.module.interfaces.ItemUpdateModule;
import webplus.ezbacklog.values.Constants;
import webplus.ezbacklog.values.ItemStatus;

import com.google.gson.Gson;

/**
 * This controller handles all item updates. The requests should comply with
 * REST convention, and the payload is json string serilizable to {@link Item}.
 * 
 */
@Controller
public class ItemUpdateController {
	@Autowired
	private Gson gson;
	@Autowired
	private ItemDisplayModule itemDisplayModule;
	@Autowired
	private ItemUpdateModule itemUpdateModule;
	@Autowired
	private ActivityUpdateModule activityUpdateModule;

	/**
	 * Handler for item creation.
	 * 
	 * @param itemString
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/f/itemUpdate", method = RequestMethod.POST)
	public String createItem(@RequestBody String itemString, Model model) {
		Item item = gson.fromJson(itemString, Item.class);
		itemUpdateModule.createItem(item);
		activityUpdateModule.addCreationAcitivity(item);
		model.addAttribute(Constants.JSON_MODEL, gson.toJson(item));
		return "json";
	}

	/**
	 * Handler for item update.
	 * 
	 * @param id
	 * @param itemString
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/f/itemUpdate/{id}", method = RequestMethod.PUT)
	public String updateItem(@RequestBody String itemString, Model model) {
		Item item = gson.fromJson(itemString, Item.class);
		itemUpdateModule.saveItem(item);
		activityUpdateModule.addUpdateActivity(item);
		model.addAttribute(Constants.JSON_MODEL, gson.toJson(item));
		return "json";
	}

	/**
	 * Handler for item deletion.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/f/itemUpdate/{id}", method = RequestMethod.DELETE)
	public String deleteItem(@PathVariable("id") Long id, Model model) {
		Item item = itemDisplayModule.getItemById(id);
		item.setStatus(ItemStatus.Deleted);
		itemUpdateModule.saveItem(item);
		model.addAttribute(Constants.JSON_MODEL, gson.toJson(item));
		return "json";
	}
}
