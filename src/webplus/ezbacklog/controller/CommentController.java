package webplus.ezbacklog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webplus.ezbacklog.model.Activity;
import webplus.ezbacklog.module.interfaces.CommentModule;
import webplus.ezbacklog.values.Constants;

import com.google.gson.Gson;

@Controller
public class CommentController {
	@Autowired
	private Gson gson;
	@Autowired
	private CommentModule commentModule;

	@RequestMapping(value = "/f/comments/{itemId}", method = RequestMethod.GET)
	public String loadComments(@PathVariable("itemId") long itemId, Model model) {
		List<Activity> comments = commentModule.loadComments(itemId);
		model.addAttribute(Constants.JSON_MODEL, gson.toJson(comments));
		return "json";
	}

	@RequestMapping(value = "/f/comments/", method = RequestMethod.POST)
	public String addComment(@RequestBody String commentJson, Model model) {
		Activity comment = gson.fromJson(commentJson, Activity.class);
		commentModule.addComment(comment);
		model.addAttribute(Constants.JSON_MODEL, gson.toJson(comment));
		return "json";
	}
}
