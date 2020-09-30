package com.lvg.tcreator.models;


import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.models.QuestionStatisticDB;
import com.lvg.tcreator.persistence.services.ExamService;
import com.lvg.tcreator.persistence.services.OrderService;
import com.lvg.tcreator.persistence.services.QuestionStatisticService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class QuestionStatisticServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    QuestionStatisticService service;
    @Autowired
    ExamService examService;

    @Test
    public void saveQuestionStatisticTest(){
        OrderDB orderDB = ModelsGenerator.getOrderDB();
        orderService.save(orderDB);
        ExamDB examDB = ModelsGenerator.getExamDB();
        examDB.setOrder(orderDB);
        examService.save(examDB);
        examDB.getTickets().forEach(examTicket -> examTicket.getQuestions()
        .forEach(question -> {
            QuestionStatisticDB q = new QuestionStatisticDB();
            q.setNdtMethod(question.getNdtMethod());
            q.setOrderId(orderDB.getId());
            q.setQuestionNumber(question.getNumber());
            q.setTestType(question.getTestTypes());
            q.setWrongAnswerCount(2);
            q.setTotalCount(4);
            service.save(q);
            assertNotNull(q.getId());

        }));
        assertEquals(3, service.findAll().size());
    }
}
