package com.lvg.tcreator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionsController {

    @GetMapping("/questions")
    String getAllQuestions(Model model){
        return "index";
    }
}
