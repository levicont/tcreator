package com.lvg.tcreator.services.impl.excel;

import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.Statistic;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.services.OrderService;
import com.lvg.tcreator.services.StatisticService;
import com.lvg.tcreator.utils.Validator;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.lvg.tcreator.config.R.ExcelProps.EXCEL_SHEET_QUESTION_NAME;
import static com.lvg.tcreator.models.Statistic.ExamTicket;


/**
 * Created by Victor Levchenko LVG Corp. on 21.04.2020.
 */
@Component
public class StatisticServiceXLS implements StatisticService {

    @Autowired
    OrderService orderService;
    @Autowired
    Validator validator;

    @Override
    public Statistic loadFromFile(byte[] file) {
        InputStream in;
        Workbook wb = null;
        try {
            in = new ByteArrayInputStream(file);
            wb = WorkbookFactory.create(in);
            validator.validateStatisticExcelFile(wb);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Sheet sheet = wb.getSheet(EXCEL_SHEET_QUESTION_NAME);

        return new Statistic(orderService.loadFromFile(file), getTicketsFromSheet(sheet));
    }

    private List<ExamTicket> getTicketsFromSheet(Sheet sheet) {
        List<ExamTicket> resultList = new ArrayList<>();

        resultList.addAll(get3ExamTicketsFromSheet(TestTypes.TOTAL_TEST, sheet));
        resultList.addAll(get3ExamTicketsFromSheet(TestTypes.SPEC_TEST, sheet));
        resultList.addAll(get3ExamTicketsFromSheet(TestTypes.SPEC_6_SECTOR_TEST, sheet));
        resultList.addAll(get3ExamTicketsFromSheet(TestTypes.SPEC_7_SECTOR_TEST, sheet));
        resultList.addAll(get3ExamTicketsFromSheet(TestTypes.SPEC_8_SECTOR_TEST, sheet));

        return resultList;
    }

    private List<ExamTicket> get3ExamTicketsFromSheet(TestTypes testTypes, Sheet sheet) {
        List<ExamTicket> resultList = new ArrayList<>();
        //Parameter i is a number of exam ticket minus 1. Because row in xlsx file starts from 0
        for (int i = 0; i < 3; i++) {
            ExamTicket ticket = getExamTicketFromExcelSheet(i, testTypes, sheet);
            if (ticket.getQuestions().isEmpty())
                continue;
            ticket.setNumber(i+1);
            resultList.add(ticket);
        }
        return resultList;
    }

    private ExamTicket getExamTicketFromExcelSheet(Integer ticketNum, TestTypes testTypes, Sheet sheet) {
        ExamTicket ticket = new ExamTicket(testTypes);
        /*
         * The rows in *.xlsx files start from 0 index. First row has index 0.
         * The first column has index 0.
         */
        final int TOTAL_TEST_ROW_NUMBER = 8;
        final int TOTAL_TEST_BEGIN_COLUMN = 15;
        final int TOTAL_TEST_END_COLUMN = 54;
        final int SPEC_TEST_ROW_NUMBER = 12;
        final int SPEC_TEST_BEGIN_COLUMN = 11;
        final int SPEC_TEST_END_COLUMN = 22;
        final int SECTOR_6_TEST_ROW_NUMBER = 21;
        final int SECTOR_6_TEST_BEGIN_COLUMN = 35;
        final int SECTOR_6_TEST_END_COLUMN = 43;
        final int SECTOR_7_TEST_ROW_NUMBER = 25;
        final int SECTOR_7_TEST_BEGIN_COLUMN = 10;
        final int SECTOR_7_TEST_END_COLUMN = 18;
        final int SECTOR_8_TEST_ROW_NUMBER = 25;
        final int SECTOR_8_TEST_BEGIN_COLUMN = 35;
        final int SECTOR_8_TEST_END_COLUMN = 43;

        if (testTypes == TestTypes.TOTAL_TEST)
            ticket.getQuestions().addAll(
                    getQuestionsFromExcelCells(
                            sheet.getRow(TOTAL_TEST_ROW_NUMBER + ticketNum), TOTAL_TEST_BEGIN_COLUMN, TOTAL_TEST_END_COLUMN));

        else if (testTypes == TestTypes.SPEC_TEST)
            ticket.getQuestions().addAll(
                    getQuestionsFromExcelCells(
                            sheet.getRow(SPEC_TEST_ROW_NUMBER + ticketNum), SPEC_TEST_BEGIN_COLUMN, SPEC_TEST_END_COLUMN));
        else if (testTypes == TestTypes.SPEC_6_SECTOR_TEST)
            ticket.getQuestions().addAll(
                    getQuestionsFromExcelCells(
                            sheet.getRow(SECTOR_6_TEST_ROW_NUMBER + ticketNum), SECTOR_6_TEST_BEGIN_COLUMN, SECTOR_6_TEST_END_COLUMN));
        else if (testTypes == TestTypes.SPEC_7_SECTOR_TEST)
            ticket.getQuestions().addAll(
                    getQuestionsFromExcelCells(
                            sheet.getRow(SECTOR_7_TEST_ROW_NUMBER + ticketNum), SECTOR_7_TEST_BEGIN_COLUMN, SECTOR_7_TEST_END_COLUMN));
        else if (testTypes == TestTypes.SPEC_8_SECTOR_TEST)
            ticket.getQuestions().addAll(
                    getQuestionsFromExcelCells(
                            sheet.getRow(SECTOR_8_TEST_ROW_NUMBER + ticketNum), SECTOR_8_TEST_BEGIN_COLUMN, SECTOR_8_TEST_END_COLUMN));

        return ticket;
    }

    private Set<Question> getQuestionsFromExcelCells(Row row, int begin, int end) {
        Set<Question> questions = new TreeSet<>();

        for (Cell currentCell : row) {
            if (currentCell.getColumnIndex() < begin)
                continue;
            //If first number of exam ticket is 0 we break the loop and not add this ticket to the list

            if (currentCell.getColumnIndex() == begin && currentCell.getNumericCellValue() == 0d) {
                break;
            }
            if (currentCell.getColumnIndex() > end)
                break;
            try {
                questions.add(new Question(Double.valueOf(currentCell.getNumericCellValue()).intValue()));
            } catch (NumberFormatException ex) {
                throw new RuntimeException("Cannot parse int value from excel cell, value: "
                        + currentCell.getStringCellValue() +
                        "\n cell column num: " + currentCell.getColumnIndex() +
                        "\n cell row num: " + currentCell.getRowIndex(), ex);
            }

        }
        //Here is deleting questions which have number 0, maybe we have to validate this case
        questions.removeIf(question -> question.getNumber() == 0);
        return questions;
    }
}
