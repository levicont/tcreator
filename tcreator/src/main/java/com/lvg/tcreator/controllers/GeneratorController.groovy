package com.lvg.tcreator.controllers

import com.lvg.tcreator.managers.OrderManager
import com.lvg.tcreator.managers.TestManager
import com.lvg.tcreator.models.Order
import com.lvg.tcreator.models.Test
import com.lvg.tcreator.utils.DateUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import javax.validation.Valid

@Controller
class GeneratorController {

    @RequestMapping(value="/generator", method=RequestMethod.GET)
	String generator(Model model, @RequestParam(value="method",required=false) String method){
		if (method == null)
			return "redirect:/"
		model.addAttribute("ndtMethod", method)
		model.addAttribute("order", OrderManager.getDefaultOrder(method))
		return "generator"
	}

	@RequestMapping(value="/generator", method=RequestMethod.POST)
	String report(@Valid @ModelAttribute Order order, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors())
			return "generator"

		model.addAttribute("ndtMethod", order.getNdtMethod().toString())
		model.addAttribute("dateOrder", DateUtil.formatDate(order.getDate()))
		TestManager tm = new TestManager(order)
		List<Test> testList = tm.createTestList()
		model.addAttribute("tests", testList)
		return "report"
	}
}
