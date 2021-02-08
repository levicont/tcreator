package com.lvg.tcreator.controllers;

import com.lvg.tcreator.models.NdtMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.lvg.tcreator.config.R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE;

@Controller
public class QuestionController {

    @GetMapping("/questions")
    String getAllQuestions(Model model){
        model.addAttribute(BODY_TEMPLATE_ATTRIBUTE, "questions");
        model.addAttribute("questionNdtMethod", NdtMethod.VT);
        return "index";
    }
}
