package com.lvg.tcreator.controllers;

import com.lvg.tcreator.managers.ExamManager;
import com.lvg.tcreator.managers.OrderManager;
import com.lvg.tcreator.managers.TestManager;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Order;
import com.lvg.tcreator.models.Test;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.lvg.tcreator.config.R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE;

/**
 * Created by Victor Levchenko (LVG Corp.) on 29.02.2020.
 */
@Controller
public class GeneratorController {
    @Autowired
    private ExamManager examManager;

    @RequestMapping(value="/generator", method= RequestMethod.GET)
    public String generator(Model model, @RequestParam(value="method",required=false) String method){
        model.addAttribute(BODY_TEMPLATE_ATTRIBUTE,"generator");
        if (method == null)
            return "redirect:/";
        model.addAttribute("ndtMethods", Arrays.asList(NdtMethod.values()));
        model.addAttribute("ndtMethod", method);
        model.addAttribute("order", OrderManager.getDefaultOrder(method));

        return "index";
    }

    @RequestMapping(value="/generator", method=RequestMethod.POST)
    public String report(@Valid @ModelAttribute Order order, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute(BODY_TEMPLATE_ATTRIBUTE, "generator");
            model.addAttribute("ndtMethod", order.getNdtMethod());
            model.addAttribute("ndtMethods", Arrays.asList(NdtMethod.values()));
            return "index";
        }
        model.addAttribute("ndtMethod", order.getNdtMethod().toString());
        model.addAttribute("dateOrder", DateUtil.formatDate(order.getDate()));
        model.addAttribute(BODY_TEMPLATE_ATTRIBUTE, "report");
        OrderDB orderDB = examManager.getOrderDB(order);
        model.addAttribute("orderDb", orderDB);
        orderDB.getExams().forEach(examDB -> {
            model.addAttribute(examDB.getTestTypes().toString(), examDB.getTestTypes());
        });
        model.addAttribute("variantCount", order.getVariantCount());
        model.addAttribute("examTickets", examManager.getAllExamTickets());
        return "index";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {

            @Override
            public String getAsText() {
                return DateUtil.formatDate((LocalDate)getValue());
            }

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(DateUtil.parseDate(text));
            }

        });
    }
}
