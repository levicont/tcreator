package com.lvg.tcreator.services.impl;

import com.lvg.tcreator.models.*;
import com.lvg.tcreator.persistence.models.ExamTicketDB;
import com.lvg.tcreator.services.ExamTicketService;
import com.lvg.tcreator.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 18.02.2021.
 */
@Component
public class ExamTicketServiceImpl implements ExamTicketService {

    @Autowired
    QuestionService questionService;

    @Override
    public ExamTicketDTO getExamTicketDtoFromDbEntity(ExamTicketDB examTicketDB) {
        ExamTicketDTO examTicketDTO = new ExamTicketDTO(examTicketDB.getTicketVariant(),
                examTicketDB.getExam().getTestTypes(),
                examTicketDB.getExam().getNdtMethod());
        examTicketDB.getQuestions().forEach(questionDB ->
                examTicketDTO.addQuestionDTO(questionService.getQuestionDtoFromDbEntity(questionDB)));
        return examTicketDTO;
    }

    @Override
    public ExamTicketDTO createExamTicketDto(Integer ticketNumber, NdtMethod ndtMethod, TestTypes testTypes, int questionCount) {
        ExamTicketDTO examTicketDTO = new ExamTicketDTO(ticketNumber,testTypes,ndtMethod);
        List<QuestionDTO> questionDTOList = questionService.findByNdtMethodAndTestType(ndtMethod, testTypes);
        examTicketDTO.getQuestionDTOSet().addAll(questionService.getRandomQuestionFromList(questionDTOList, questionCount));
        return examTicketDTO;
    }

    @Override
    public List<ExamTicketDTO> getExamTicketsFromOrderDto(OrderDTO orderDTO) {
        List<ExamTicketDTO> examTicketDTOList = new ArrayList<>();
        orderDTO.getExamDTOList().forEach(examDTO -> {
            examTicketDTOList.addAll(examDTO.getExamTicketDTOList());
        });
        return examTicketDTOList;
    }
}
