package com.lvg.tcreator.config;


import com.lvg.tcreator.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitializer
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private QuestionService questionService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        questionService.storeAllQuestionsInDB();

    }
}