package com.pengurus.crm.server;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("*.htm")
public class PageController {
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
}
