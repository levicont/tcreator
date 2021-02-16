package com.lvg.tcreator.services.impl.excel;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.OrderDTO;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.services.OrderService;
import com.lvg.tcreator.utils.Validator;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.lvg.tcreator.config.R.ExcelProps.EXCEL_SHEET_ORDER_NAME;

/**
 * Created by Victor Levchenko LVG Corp. on 26.04.2020.
 */
@Component
public class OrderServiceXLS implements OrderService {

    @Autowired
    Validator validator;

    @Override
    public OrderDTO loadFromFile(byte[] file) {
        InputStream in = new ByteArrayInputStream(file);
        Workbook workbook = null;

        try {
            workbook = WorkbookFactory.create(in);
            validator.validateStatisticExcelFile(workbook);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        Sheet sheet = workbook.getSheet(EXCEL_SHEET_ORDER_NAME);

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setNumber(getOrderNumberFromSheet(sheet));
        orderDTO.setDate(getOrderDateFromSheet(sheet));
        orderDTO.setNdtMethod(getNdtMethodFromSheet(sheet));

        Map<TestTypes, Boolean> testTypesMap = getTestTypesFromSheet(sheet);
        if (testTypesMap.containsKey(TestTypes.TOTAL_TEST)) orderDTO.setIsTotalTest(testTypesMap.get(TestTypes.TOTAL_TEST));
        if (testTypesMap.containsKey(TestTypes.SPEC_TEST)) orderDTO.setIsTotalTest(testTypesMap.get(TestTypes.SPEC_TEST));
        if (testTypesMap.containsKey(TestTypes.SPEC_6_SECTOR_TEST)) orderDTO.setIsTotalTest(testTypesMap.get(TestTypes.SPEC_6_SECTOR_TEST));
        if (testTypesMap.containsKey(TestTypes.SPEC_7_SECTOR_TEST)) orderDTO.setIsTotalTest(testTypesMap.get(TestTypes.SPEC_7_SECTOR_TEST));
        if (testTypesMap.containsKey(TestTypes.SPEC_8_SECTOR_TEST)) orderDTO.setIsTotalTest(testTypesMap.get(TestTypes.SPEC_8_SECTOR_TEST));

        return orderDTO;
    }

    private String getOrderNumberFromSheet(Sheet sheet){
        final int ORDER_NUMBER_ROW_INDEX =  6;
        final int ORDER_NUMBER_COLUMN_INDEX =  3;

        Cell cell = sheet.getRow(ORDER_NUMBER_ROW_INDEX).getCell(ORDER_NUMBER_COLUMN_INDEX);
        return cell.getStringCellValue();
    }

    private LocalDate getOrderDateFromSheet(Sheet sheet){
        final int ORDER_DATE_ROW_INDEX =  30;
        final int ORDER_DATE_COLUMN_INDEX =  5;

        Cell cell = sheet.getRow(ORDER_DATE_ROW_INDEX).getCell(ORDER_DATE_COLUMN_INDEX);
        return cell.getLocalDateTimeCellValue().toLocalDate();

    }

    private NdtMethod getNdtMethodFromSheet(Sheet sheet){
        final int ORDER_NDT_METHOD_ROW_INDEX =  9;
        final int ORDER_NDT_METHOD_COLUMN_INDEX =  3;

        Cell cell = sheet.getRow(ORDER_NDT_METHOD_ROW_INDEX).getCell(ORDER_NDT_METHOD_COLUMN_INDEX);
        return NdtMethod.valueOf(cell.getStringCellValue());

    }

    private Map<TestTypes, Boolean> getTestTypesFromSheet(Sheet sheet){
        final int ORDER_SECTOR_START_ROW_INDEX = 9;
        final int ORDER_SECTOR_END_ROW_INDEX = 19;
        final int ORDER_6_SECTOR_COLUMN_INDEX = 10;
        final int ORDER_7_SECTOR_COLUMN_INDEX = 11;
        final int ORDER_8_SECTOR_COLUMN_INDEX = 12;
        final String SECTOR_MARK_TRUE = "+";

        Map<TestTypes, Boolean> testTypesMap = new HashMap<>();
        testTypesMap.put(TestTypes.TOTAL_TEST, Boolean.TRUE);
        testTypesMap.put(TestTypes.SPEC_TEST,Boolean.TRUE);
        for(int i = ORDER_SECTOR_START_ROW_INDEX; i <= ORDER_SECTOR_END_ROW_INDEX; i++){
            Row row = sheet.getRow(i);
            if (!testTypesMap.containsKey(TestTypes.SPEC_6_SECTOR_TEST)){
                if(row.getCell(ORDER_6_SECTOR_COLUMN_INDEX).getStringCellValue().equals(SECTOR_MARK_TRUE))
                    testTypesMap.put(TestTypes.SPEC_6_SECTOR_TEST, Boolean.TRUE);
            }

            if (!testTypesMap.containsKey(TestTypes.SPEC_7_SECTOR_TEST)){
                if(row.getCell(ORDER_7_SECTOR_COLUMN_INDEX).getStringCellValue().equals(SECTOR_MARK_TRUE))
                    testTypesMap.put(TestTypes.SPEC_7_SECTOR_TEST, Boolean.TRUE);
            }

            if (!testTypesMap.containsKey(TestTypes.SPEC_8_SECTOR_TEST)){
                if(row.getCell(ORDER_8_SECTOR_COLUMN_INDEX).getStringCellValue().equals(SECTOR_MARK_TRUE))
                    testTypesMap.put(TestTypes.SPEC_8_SECTOR_TEST, Boolean.TRUE);
            }
        }

        return testTypesMap;
    }
}
