package webplus.ezbacklog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

/**
 * Loads and updates related item changes.
 * 
 */
@Controller
public class RelatedItemController {
	@Autowired
	private Gson gson;

	@RequestMapping(value = "/f/relatedItems/{id}", method = RequestMethod.GET)
	public String loadAllItems(Model model) {
		
		return "json";
	}
}
