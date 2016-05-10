package com.lvg.tcreator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tcreator.config.R;
import com.lvg.tcreator.exceptions.TCreatorException;

@Controller
public class ErrorController {
	
	@RequestMapping("/errors/404.html")
	public ModelAndView error404(Model model)throws TCreatorException{
		throw new TCreatorException(R.Exceptions.ERROR_MSG_404);
	}
	
	@RequestMapping("/errors/500.html")
	public ModelAndView error500(Model model)throws TCreatorException{
		throw new TCreatorException(R.Exceptions.ERROR_MSG_500);
	}
}
