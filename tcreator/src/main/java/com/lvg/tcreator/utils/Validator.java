package com.lvg.tcreator.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import static com.lvg.tcreator.config.R.ExcelProps.*;


/**
 * Created by Victor Levchenko LVG Corp. on 26.04.2020.
 */
public class Validator {

    public static String EXCEL_WORKBOOK_NULL_ERR_MSG = "Excel Workbook is null";
    public static String EXCEL_QUESTION_SHEET_NULL_ERR_MSG = "Excel question sheet is null";
    public static String EXCEL_ORDER_SHEET_NULL_ERR_MSG = "Excel order sheet is null";



    public static void validateStatisticExcelFile(Workbook workbook){
        if (workbook == null) throw new IllegalArgumentException(EXCEL_WORKBOOK_NULL_ERR_MSG);
        Sheet questionSheet = workbook.getSheet(EXCEL_SHEET_QUESTION_NAME);
        if (questionSheet == null) throw new IllegalArgumentException(EXCEL_QUESTION_SHEET_NULL_ERR_MSG);
        Sheet orderSheet = workbook.getSheet(EXCEL_SHEET_ORDER_NAME);
        if (orderSheet == null) throw new IllegalArgumentException(EXCEL_ORDER_SHEET_NULL_ERR_MSG);

    }
}
