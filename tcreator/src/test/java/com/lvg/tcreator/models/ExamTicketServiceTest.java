package com.lvg.tcreator.models;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.ExamTicketDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.services.ExamTicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ExamTicketServiceTest {

    @Autowired
    private ExamTicketService service;

    @Test
    public void saveExamTicketTest(){
        ExamTicketDB ticket = GeneratorModels.getExamTicketDB();
        assertNull(ticket.getId());
        service.save(ticket);
        assertNotNull(ticket.getId());

    }

    @Test
    public void updateExamTicketTest() {
        ExamTicketDB ticket = GeneratorModels.getExamTicketDB();
        service.save(ticket);

        Long id = ticket.getId();
        Set<QuestionDB> oldQuestions = ticket.getQuestions();
        int oldAnswerVariantsCount = oldQuestions.size();

        QuestionDB newQuestion = GeneratorModels.getQuestionDB();
        newQuestion.setQuestionText("New question");
        newQuestion.setQuestionNumber(5);

        ticket.getQuestions().add(newQuestion);
        service.save(ticket);

        ticket = service.find(id);
        assertNotEquals(oldAnswerVariantsCount, ticket.getQuestions().size());
    }

    @Test(expected = TCreatorException.class)
    public void deleteExamTicketTest(){
        ExamTicketDB ticket = GeneratorModels.getExamTicketDB();
        service.save(ticket);
        Long id = ticket.getId();
        assertNotNull(id);

        ExamTicketDB ticket2 = service.find(id);
        service.delete(ticket2);
        service.find(id);
    }

    @Test
    public void findAllExamTicketTest(){
        Long count = (long)service.findAll().size();
        service.save(GeneratorModels.getExamTicketDB());
        assertEquals(count+1, (long)service.findAll().size());
    }



}
