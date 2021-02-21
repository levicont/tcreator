package com.lvg.tcreator.controllers;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.OrderDTO;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.services.ExamTicketService;
import com.lvg.tcreator.services.OrderService;
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

import static com.lvg.tcreator.config.R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE;

/**
 * Created by Victor Levchenko (LVG Corp.) on 29.02.2020.
 */
@Controller
public class GeneratorController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ExamTicketService examTicketService;

    @GetMapping(value="/generator")
    public String generator(Model model, @RequestParam(value="method",required=false) NdtMethod method){
        model.addAttribute(BODY_TEMPLATE_ATTRIBUTE,"generator");
        if (method == null)
            return "redirect:/";
        model.addAttribute("ndtMethods", Arrays.asList(NdtMethod.values()));
        model.addAttribute("ndtMethod", method.toString());
        model.addAttribute("orderDTO", orderService.getDefaultOrder(method));
        return "index";
    }

    @PostMapping(value="/generator")
    public String report(@Valid @ModelAttribute OrderDTO orderDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute(BODY_TEMPLATE_ATTRIBUTE, "generator");
            model.addAttribute("ndtMethod", orderDTO.getNdtMethod());
            model.addAttribute("ndtMethods", Arrays.asList(NdtMethod.values()));
            return "index";
        }
        orderDTO = orderService.generateExams(orderDTO,orderDTO.getVariantCount(),
                orderDTO.getTestTypesSet().toArray(new TestTypes[0]));
        model.addAttribute(BODY_TEMPLATE_ATTRIBUTE, "report");
        model.addAttribute("ndtMethod", orderDTO.getNdtMethod().toString());
        model.addAttribute("dateOrder", DateUtil.formatDate(orderDTO.getDate()));
        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("variantCount", orderDTO.getVariantCount());
        model.addAttribute("examTickets", examTicketService.getExamTicketsFromOrderDto(orderDTO));
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
