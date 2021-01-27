package com.lvg.tcreator.controllers;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tcreator.config.R;
import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.managers.OrderManager;
import com.lvg.tcreator.managers.TestManager;
import com.lvg.tcreator.models.Order;
import com.lvg.tcreator.models.Test;
import com.lvg.tcreator.models.User;
import com.lvg.tcreator.utils.DateUtil;


//@Controller
public class MainController {
private final String GREETING_STRING = "Добро пожаловать на сайт генератора тестов!";	

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login()throws TCreatorException{
		ModelAndView mv = new ModelAndView("login");
		List<User> names = new ArrayList<>();
		names.add(new User("Вася", 20));
		names.add(new User("Петя", 25));
		names.add(new User("Коля", 28));
		mv.addObject("users", names);
		throw new TCreatorException(R.Exceptions.ERROR_MSG_PAGE_INACCESSABLE);
		//return mv;
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
		model.addAttribute("order", OrderManager.getDefaultOrder(method));		
		return "generator";
	}
	
	@RequestMapping(value="/generator", method=RequestMethod.POST)
	public String report(@Valid @ModelAttribute Order order, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors())
			return "generator";
		
		model.addAttribute("ndtMethod", order.getNdtMethod().toString());
		model.addAttribute("dateOrder", DateUtil.formatDate(order.getDate()));
		TestManager tm = new TestManager(order);			
		List<Test> testList = tm.createTestList();		
		model.addAttribute("tests", testList);
		return "report";		
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

			@Override
			public String getAsText() {
				return DateUtil.formatDate((Date)getValue());
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(DateUtil.parseDate(text));
			}
						
		});
	}

	private Boolean isValidDB(){
		return false;
	}
	
	
	
	
	
	
}
