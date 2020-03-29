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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

import static com.lvg.tcreator.config.R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE;
import static com.lvg.tcreator.config.R.GlobalAttributes.ERROR_MESSAGE_ATTRIBUTE;

/**
 * Created by Victor Levchenko (LVG Corp.) on 29.02.2020.
 */
@Controller
public class GeneratorController {
    @RequestMapping(value="/generator", method= RequestMethod.GET)
    public String generator(Model model, @RequestParam(value="method",required=false) String method){
        model.addAttribute(BODY_TEMPLATE_ATTRIBUTE,"generator");
        if (method == null)
            return "redirect:/";
        model.addAttribute("ndtMethod", method);
        model.addAttribute("order", OrderManager.getDefaultOrder(method));

        return "index";
    }

    @RequestMapping(value="/generator", method=RequestMethod.POST)
    public String report(@Valid @ModelAttribute Order order, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute(BODY_TEMPLATE_ATTRIBUTE, "generator");
            model.addAttribute("ndtMethod", order.getNdtMethod());
            return "index";
        }
        model.addAttribute("ndtMethod", order.getNdtMethod().toString());
        model.addAttribute("dateOrder", DateUtil.formatDate(order.getDate()));
        model.addAttribute(BODY_TEMPLATE_ATTRIBUTE, "report");
        TestManager tm = new TestManager(order);
        List<Test> testList = tm.createTestList();
        model.addAttribute("tests", testList);
        return "index";
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
}
