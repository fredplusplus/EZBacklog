package webplus.ezbacklog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webplus.ezbacklog.model.RelatedItem;
import webplus.ezbacklog.module.interfaces.RelatedItemModule;
import webplus.ezbacklog.values.Constants;

import com.google.gson.Gson;

/**
 * Loads and updates related item changes.
 * 
 */
@Controller
public class RelatedItemController {
	@Autowired
	private Gson gson;
	@Autowired
	private RelatedItemModule relatedItemModule;

	@RequestMapping(value = "/f/relatedItems/{itemId}", method = RequestMethod.GET)
	public String loadAllItems(@PathVariable("itemId") Long itemId, Model model) {
		List<RelatedItem> relatedItems = relatedItemModule.fecthRelatedItems(itemId);
		model.addAttribute(Constants.JSON_MODEL, gson.toJson(relatedItems));
		return "json";
	}
}
