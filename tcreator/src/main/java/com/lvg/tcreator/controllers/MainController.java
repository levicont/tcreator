package com.lvg.tcreator.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tcreator.models.User;


@Controller
public class MainController {
private final String GREETING_STRING = "Hello in test site!";
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("login");
		List<User> names = new ArrayList<>();
		names.add(new User("Коля", 20));
		names.add(new User("Петя", 25));
		names.add(new User("Вася", 28));
		mv.addObject("users", names);
		return mv;
	}
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("home");
		
		mv.addObject("greeting", GREETING_STRING);
		return mv;
	}
	
	
	
}
