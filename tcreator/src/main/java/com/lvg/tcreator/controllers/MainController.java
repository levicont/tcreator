package com.lvg.tcreator.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Order;
import com.lvg.tcreator.models.User;
import com.lvg.tcreator.utils.DateUtil;


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
		model.addAttribute("order", getDefaultOrder(method));		
		return "generator";
	}
	
	@RequestMapping(value="/generator", method=RequestMethod.POST)
	public String report(@ModelAttribute Order order, Model model){
		model.addAttribute("ndtMethod", order.getNdtMethod().toString());
		model.addAttribute("dateOrder", DateUtil.formatDate(order.getDate()));
		return "report";
	}	
	
	
	
	private Order getDefaultOrder(String ndtMethod){
		Order order = new Order();
		order.setDate(new Date());
		order.setNdtMethod(NdtMethod.valueOf(ndtMethod));
		int month = order.getDate().getMonth()+1;	
		StringBuilder number = new StringBuilder();
		if (month < 10)
			number.append("0"+month);
		else
			number.append(month);
		number.append("\\01");		
	
		order.setNumber(number.toString());
		order.setVariantCount(1);
		order.setIsTotalTest(true);
		order.setIsSpecTest(true);
		
		return order;
	}
	
	
	
}
