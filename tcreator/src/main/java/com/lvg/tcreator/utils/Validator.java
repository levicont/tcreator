package com.lvg.tcreator.utils;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.persistence.services.QuestionService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lvg.tcreator.config.R.ExcelProps.EXCEL_SHEET_ORDER_NAME;
import static com.lvg.tcreator.config.R.ExcelProps.EXCEL_SHEET_QUESTION_NAME;


/**
 * Created by Victor Levchenko LVG Corp. on 26.04.2020.
 */
@Component
public class Validator {

    private static String EXCEL_WORKBOOK_NULL_ERR_MSG = "Excel Workbook is null";
    private static String EXCEL_QUESTION_SHEET_NULL_ERR_MSG = "Excel question sheet is null";
    private static String EXCEL_ORDER_SHEET_NULL_ERR_MSG = "Excel order sheet is null";

    @Autowired
    private QuestionService questionDBService;
    @Autowired
    private com.lvg.tcreator.services.QuestionService questionModelService;


    public void validateStatisticExcelFile(Workbook workbook){
        if (workbook == null) throw new IllegalArgumentException(EXCEL_WORKBOOK_NULL_ERR_MSG);
        Sheet questionSheet = workbook.getSheet(EXCEL_SHEET_QUESTION_NAME);
        if (questionSheet == null) throw new IllegalArgumentException(EXCEL_QUESTION_SHEET_NULL_ERR_MSG);
        Sheet orderSheet = workbook.getSheet(EXCEL_SHEET_ORDER_NAME);
        if (orderSheet == null) throw new IllegalArgumentException(EXCEL_ORDER_SHEET_NULL_ERR_MSG);

    }

    public List<NdtMethod> validateQuestions(){
        List<NdtMethod> ndtMethodsWithInvalidCount = new ArrayList<>();
        Arrays.stream(NdtMethod.values()).forEach(ndtMethod ->
        {
            if (!isCorrectQuestionsCount(ndtMethod))
                ndtMethodsWithInvalidCount.add(ndtMethod);
        });

        return ndtMethodsWithInvalidCount;

    }

    private boolean isCorrectQuestionsCount(NdtMethod ndtMethod){
        Integer dbQuestionsCount = questionDBService.findByNdtMethod(ndtMethod).size();
        Integer modelQuestionsCount = questionModelService.findByNdtMethod(ndtMethod).size();
        return dbQuestionsCount.equals(modelQuestionsCount);
    }
}
