package webplus.ezbacklog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/springTest")
	public String execute() {
		return "MyView";
	}
}
