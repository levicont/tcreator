package com.lvg.tcreator.services.impl;

import com.lvg.tcreator.models.ExamDTO;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.OrderDTO;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.services.ExamService;
import com.lvg.tcreator.services.ExamTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

import static com.lvg.tcreator.config.R.ExamProperties.*;
import static com.lvg.tcreator.config.R.ExamProperties.SPEC_TEST_ONE_SECTOR_QUESTIONS_COUNT;

/**
 * Created by Victor Levchenko (LVG Corp.) on 18.02.2021.
 */

@Component
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamTicketService examTicketService;

    @Override
    public ExamDTO getExamDtoFromExamDb(ExamDB examDB) {
        ExamDTO examDTO = new ExamDTO(examDB.getTestTypes(), examDB.getNdtMethod());
        examDB.getTickets().forEach(examTicketDB ->
                examDTO.addExamTicketDTO(examTicketService.getExamTicketDtoFromDbEntity(examTicketDB)));
        return examDTO;
    }

    @Override
    public ExamDTO createExamDTO(OrderDTO orderDTO, TestTypes testTypes, Integer ticketCount) {
        ExamDTO examDTO = new ExamDTO(testTypes, orderDTO.getNdtMethod());
        int questionsCount = getQuestionsCount(orderDTO.getNdtMethod(), testTypes, orderDTO.getSectorsCount());
        IntStream.range(0, ticketCount).forEach(index ->
                examDTO.addExamTicketDTO(examTicketService
                        .createExamTicketDto(index+1, orderDTO.getNdtMethod(), testTypes, questionsCount)));
        return examDTO;
    }

    private int getQuestionsCount(NdtMethod ndtMethod, TestTypes testType, int sectorsCount) {

        if (testType == TestTypes.TOTAL_TEST) {
            if (ndtMethod == NdtMethod.RT || ndtMethod == NdtMethod.UT)
                return TOTAL_TEST_UT_RT_QUESTIONS_COUNT;
            else
                return TOTAL_TEST_MT_VT_PT_QUESTIONS_COUNT;
        } else if (testType == TestTypes.SPEC_TEST) {
            return SPEC_TEST_QUESTIONS_COUNT;
        } else if (sectorsCount == 3) {
            return SPEC_TEST_THREE_SECTOR_QUESTIONS_COUNT;
        } else if (sectorsCount == 2) {
            return SPEC_TEST_TWO_SECTOR_QUESTIONS_COUNT;
        } else
            return SPEC_TEST_ONE_SECTOR_QUESTIONS_COUNT;
    }
}
