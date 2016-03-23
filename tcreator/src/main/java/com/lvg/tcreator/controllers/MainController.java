package com.lvg.tcreator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	private final String HOME_PAGE_VIEW_NAME = "home";
	public MainController(){}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName(HOME_PAGE_VIEW_NAME);
		
		return mv;
	}
	

}
