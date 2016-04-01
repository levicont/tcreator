package com.lvg.tcreator.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tcreator.models.User;


@Controller
public class MainController {
private final String GREETING_STRING = "Добро пожаловать на сайт генератора тестов!";
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("login");
		List<User> names = new ArrayList<>();
		names.add(new User("Вася", 20));
		names.add(new User("Петя", 25));
		names.add(new User("Коля", 28));
		mv.addObject("users", names);
		return mv;
	}
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("home");
		
		mv.addObject("greeting", GREETING_STRING);
		return mv;
	}
	
	@RequestMapping(value="/generator", method=RequestMethod.GET)
	public String generator(Model model, @RequestParam(value="method",required=false) String method){
		if (method == null)
			return "redirect:/";
		model.addAttribute("ndtMethod", method);
		
		return "generator";
	}
	
	
	
	
}
