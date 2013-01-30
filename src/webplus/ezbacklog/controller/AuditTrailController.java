package webplus.ezbacklog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webplus.ezbacklog.model.Activity;
import webplus.ezbacklog.module.interfaces.ActivityUpdateModule;
import webplus.ezbacklog.values.Constants;

import com.google.gson.Gson;

@Controller
public class AuditTrailController {

	@Autowired
	private Gson gson;
	@Autowired
	private ActivityUpdateModule activityModule;

	@RequestMapping(value = "/f/audit/{itemId}", method = RequestMethod.GET)
	public String fetchAuditTrail(@PathVariable("itemId") Long itemId, Model model) {
		List<Activity> activities = activityModule.getAuditTrails(itemId);
		model.addAttribute(Constants.JSON_MODEL, gson.toJson(activities));
		return "json";
	}
}
