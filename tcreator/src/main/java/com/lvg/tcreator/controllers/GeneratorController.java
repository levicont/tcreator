package com.lvg.tcreator.controllers;

import com.lvg.tcreator.config.R;
import com.lvg.tcreator.managers.OrderManager;
import com.lvg.tcreator.managers.TestManager;
import com.lvg.tcreator.models.Order;
import com.lvg.tcreator.models.Test;
import com.lvg.tcreator.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 29.02.2020.
 */
@Controller
public class GeneratorController {
    @RequestMapping(value="/generator", method= RequestMethod.GET)
    public String generator(Model model, @RequestParam(value="method",required=false) String method){
        model.addAttribute(R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE,"generator");
        if (method == null)
            return "redirect:/";
        model.addAttribute("ndtMethod", method);
        model.addAttribute("order", OrderManager.getDefaultOrder(method));

        return "index";
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
}
