package com.lvg.tcreator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	private final String HOME_PAGE_VIEW_NAME = "home";	
	
	@RequestMapping({"/","/home"})
	public String index(ModelMap model){
				
		return HOME_PAGE_VIEW_NAME;
	}
	

}
