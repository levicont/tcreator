package com.lvg.tcreator.controllers;

import com.lvg.tcreator.config.R;
import com.lvg.tcreator.models.Statistic;
import com.lvg.tcreator.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Victor Levchenko LVG Corp. on 06.04.2020.
 */
@Controller
public class StatisticController {
    private static final String STATISTIC_PAGE = "statistic";

    @Autowired
    StatisticService statisticService;


    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String statistic(Model model){
        model.addAttribute(R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE, STATISTIC_PAGE);
        return "index";
    }

    @PostMapping("/uploadStatSrcXLS")
    public String uploadStatisticSourceXLS(@RequestParam("file")MultipartFile multipartFile,Model model,
                                           RedirectAttributes redirectAttributes){
        model.addAttribute(R.GlobalAttributes.BODY_TEMPLATE_ATTRIBUTE, STATISTIC_PAGE);
        if(multipartFile.isEmpty()){
            redirectAttributes.addFlashAttribute("message","File not checked");
            return "index";
        }
        try{
            // Get the file and save it somewhere
            byte[] bytes = multipartFile.getBytes();
            //Path path = Paths.get("/home/lvg/tmp/tcreator-files/" + multipartFile.getOriginalFilename());
            //Files.write(path, bytes);
            Statistic statistic = statisticService.loadFromFile(bytes);
            model.addAttribute("fileLoaded",Boolean.TRUE);
            model.addAttribute("fileName","'" + multipartFile.getOriginalFilename() + "'");
            model.addAttribute("currentStatistic", statistic);
            //redirectAttributes.addFlashAttribute("fileName","'" + multipartFile.getOriginalFilename() + "'");

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return "index";
    }
}
