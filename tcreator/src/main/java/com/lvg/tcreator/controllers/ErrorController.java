package com.lvg.tcreator.controllers;

import com.lvg.tcreator.config.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
	private static final String ERROR_PATH = "/error";

	@RequestMapping(ERROR_PATH)
	public String errorMethod(Model model, HttpServletRequest request, HttpServletResponse response){

		model.addAttribute(R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE, "error");
		model.addAttribute("ErrorCode",request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		model.addAttribute("ErrorMessage",request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
		return "index";
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
