package webplus.ezbacklog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import webplus.ezbacklog.model.SiteNav;

import com.google.gson.Gson;

@Controller
public class ProjectController {

	@Autowired private Gson gson;

	
	@RequestMapping("/f/projects")
	public String execute(Model model) {
		SiteNav sitenav = new SiteNav();
		sitenav.setProject(true);
		model.addAttribute(SiteNav.BEAN_NAME, gson.toJson(sitenav));
		
		return "project";
	}
}
