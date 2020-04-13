package com.lvg.tcreator.controllers;

import com.lvg.tcreator.config.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Victor Levchenko LVG Corp. on 06.04.2020.
 */
@Controller
public class StatisticController {

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String statistic(Model model){
        model.addAttribute(R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE, "statistic");
        return "index";
    }
}
