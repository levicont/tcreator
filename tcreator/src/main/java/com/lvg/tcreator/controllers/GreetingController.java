package com.lvg.tcreator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String index(String name, Model model) {
        model.addAttribute("body_content","main");
        model.addAttribute("tattr","TEST ATTRIBUTE");
        return "index";
    }


}
