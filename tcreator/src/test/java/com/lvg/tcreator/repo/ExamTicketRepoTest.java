package com.lvg.tcreator.repo;

import com.lvg.tcreator.GenericTest;
import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.ExamTicketDB;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.repositories.ExamRepository;
import com.lvg.tcreator.persistence.repositories.ExamTicketRepository;
import com.lvg.tcreator.persistence.repositories.OrderRepository;
import com.lvg.tcreator.persistence.repositories.QuestionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.lvg.tcreator.repo.ModelGenerator.*;
import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 31.01.2021.
 */
@Transactional
public class ExamTicketRepoTest extends GenericTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ExamTicketRepository examTicketRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void saveExamTicketTest(){
        ExamTicketDB examTicketDB = getExamTicket();
        ExamDB examDB = getExamDB();
        OrderDB orderDB = getOrderDB();
        examDB.addExamTicket(examTicketDB);
        orderDB.addExam(examDB);
        orderDB = orderRepository.saveAndFlush(orderDB);
        assertNotNull(orderDB.getId());
        assertNotNull(examDB.getId());
        assertNotNull(examTicketDB.getId());
    }

    @Test
    public void updateExamTicketTest(){
        ExamTicketDB examTicketDB = getExamTicketWithExamAndOrder();
        examTicketDB = examTicketRepository.saveAndFlush(examTicketDB);
        assertNotNull(examTicketDB.getId());
        assertEquals(1,examTicketDB.getTicketVariant().intValue());
        examTicketDB.setTicketVariant(2);
        examTicketDB = examTicketRepository.saveAndFlush(examTicketDB);
        assertEquals(2, examTicketDB.getTicketVariant().intValue());
    }

    @Test
    public void deleteExamTicketTest(){
        ExamTicketDB examTicketDB1 = getExamTicketWithExamAndOrder();
        examTicketDB1 = examTicketRepository.saveAndFlush(examTicketDB1);
        ExamTicketDB examTicketDB2 = getExamTicket();
        examTicketDB2.setTicketVariant(2);
        examTicketDB1.getExam().addExamTicket(examTicketDB2);
        examTicketDB2 = examTicketRepository.saveAndFlush(examTicketDB2);
        assertEquals(2, examTicketRepository.getByExam(examTicketDB1.getExam()).size());
        Long id = examTicketDB2.getId();
        examTicketDB1.getExam().removeExamTicket(examTicketDB2);
        examRepository.saveAndFlush(examTicketDB1.getExam());
        assertEquals(1, examTicketRepository.getByExam(examTicketDB1.getExam()).size());
        Optional<ExamTicketDB> optional = examTicketRepository.findById(id);
        assertFalse(optional.isPresent());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveExamTicketWithoutExam(){
        ExamTicketDB examTicketDB = getExamTicket();
        examTicketRepository.saveAndFlush(examTicketDB);
    }

    @Test
    public void addRemoveQuestionsToExamTicketTest(){
        ExamTicketDB examTicketDB = getExamTicketWithExamAndOrder();
        examTicketDB = examTicketRepository.saveAndFlush(examTicketDB);
        assertNotNull(examTicketDB.getId());
        QuestionDB questionDB1 = getQuestion();
        questionDB1 = questionRepository.save(questionDB1);
        examTicketDB.addQuestion(questionDB1);

        QuestionDB question2 = getQuestion();
        question2 = questionRepository.save(question2);
        examTicketDB.addQuestion(question2);

        examTicketDB = examTicketRepository.saveAndFlush(examTicketDB);
        assertEquals(2, examTicketDB.getQuestions().size());

        examTicketDB.removeQuestion(question2);
        examTicketDB = examTicketRepository.saveAndFlush(examTicketDB);
        assertEquals(1, examTicketDB.getQuestions().size());
        assertTrue(questionRepository.findById(question2.getId()).isPresent());
    }

    private ExamTicketDB getExamTicketWithExamAndOrder(){
        ExamTicketDB examTicketDB = getExamTicket();
        ExamDB examDB = getExamDB();
        OrderDB orderDB = getOrderDB();
        examDB.addExamTicket(examTicketDB);
        orderDB.addExam(examDB);
        return examTicketDB;
    }
}
