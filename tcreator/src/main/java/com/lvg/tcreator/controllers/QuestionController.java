package com.lvg.tcreator.controllers;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.services.QuestionDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

import static com.lvg.tcreator.config.R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE;

@Controller
public class QuestionController {

    @Autowired
    private QuestionDBService questionDbService;

    @GetMapping("/questions")
    String getAllQuestions(Model model){

        Map<NdtMethod, Map<TestTypes, Integer>> ndtMethodCountMap = new TreeMap<>();

        Arrays.asList(NdtMethod.values()).forEach(method ->{
            Map<TestTypes, Integer> testTypeCountMap = new TreeMap<>();
            Arrays.asList(TestTypes.values()).forEach(testType->{
                int count = questionDbService.findByNdtMethodTestTypes(method,testType).size();
                testTypeCountMap.put(testType, count);
            });
            ndtMethodCountMap.put(method, testTypeCountMap);
        });

        model.addAttribute(BODY_TEMPLATE_ATTRIBUTE, "questions");
        model.addAttribute("questionNdtMethod", NdtMethod.VT);
        model.addAttribute("ndtMethodCountMap", ndtMethodCountMap);


        return "index";
    }
}
