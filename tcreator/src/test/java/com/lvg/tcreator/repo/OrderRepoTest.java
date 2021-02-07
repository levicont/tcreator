package com.lvg.tcreator.repo;

import com.lvg.tcreator.GenericTest;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.ExamTicketDB;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.repositories.ExamRepository;
import com.lvg.tcreator.persistence.repositories.OrderRepository;
import com.lvg.tcreator.persistence.repositories.QuestionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.function.Predicate;

import static com.lvg.tcreator.repo.ModelGenerator.*;
import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 29.01.2021.
 */
@Transactional
public class OrderRepoTest extends GenericTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void orderSaveTest(){
        OrderDB orderDB = getOrderDB();
        orderDB = orderRepository.save(orderDB);
        assertNotNull(orderDB.getId());
    }

    @Test
    public void orderUpdateTest(){
        OrderDB orderDB = getOrderDB();
        orderDB = orderRepository.save(orderDB);
        assertNotNull(orderDB.getId());
        orderDB.setNdtMethod(NdtMethod.RT);
        orderDB = orderRepository.save(orderDB);
        assertEquals(orderDB.getNdtMethod(), NdtMethod.RT);
    }

    @Test
    public void orderDeleteTest(){
        long countBeforeSave = orderRepository.count();
        OrderDB orderDB = getOrderDB();
        orderDB = orderRepository.save(orderDB);
        long countBeforeDelete = orderRepository.count();
        assertEquals(countBeforeSave+1,countBeforeDelete);
        orderRepository.delete(orderDB);
        long countAfterDelete = orderRepository.count();
        assertEquals(countBeforeSave, countAfterDelete);
    }

    @Test(expected = EntityNotFoundException.class)
    public void addRemoveExamTest(){
        ExamDB examDB1 = getExamDB();
        ExamDB examDB2 = getExamDB();
        examDB2.setTestTypes(TestTypes.SPEC_TEST);

        OrderDB orderDB = getOrderDB();
        orderDB.addExam(examDB1);
        orderDB.addExam(examDB2);
        orderDB = orderRepository.save(orderDB);
        assertNotNull(orderDB.getId());
        assertNotNull(examDB1.getId());
        assertNotNull(examDB2.getId());
        assertEquals(2, orderDB.getExams().size());

        orderDB.removeExam(examDB2);
        orderDB = orderRepository.saveAndFlush(orderDB);
        assertEquals(1, orderDB.getExams().size());
        assertEquals(examDB1, orderDB.getExams().get(0));
        ExamDB examDB3 = examRepository.getOne(examDB2.getId());
        assertNull(examDB3);
    }

    @Test
    public void fullOrderSaveTest(){
        OrderDB orderDB = getOrderDB();
        ExamDB examTotal = getExamDB(TestTypes.TOTAL_TEST);
        ExamDB examSpec = getExamDB(TestTypes.SPEC_TEST);
        ExamDB examSpec_6 = getExamDB(TestTypes.SPEC_6_SECTOR_TEST);

        ExamTicketDB examTicketTotal1 = getExamTicket();
        ExamTicketDB examTicketTotal2 = getExamTicket();
        QuestionDB questionTotal1 = getQuestion(TestTypes.TOTAL_TEST, DEFAULT_NDT_METHOD);
        questionTotal1.getAnswerVariants().addAll(getAnswers(4));
        questionTotal1 = questionRepository.save(questionTotal1);
        QuestionDB questionTotal2 = getQuestion(TestTypes.TOTAL_TEST, DEFAULT_NDT_METHOD);
        questionTotal2.getAnswerVariants().addAll(getAnswers(4));
        questionTotal2 = questionRepository.save(questionTotal2);
        examTotal.addExamTicket(examTicketTotal1);
        examTotal.addExamTicket(examTicketTotal2);
        orderDB.addExam(examTotal);
        examTicketTotal1.addQuestion(questionTotal1);
        examTicketTotal1.addQuestion(questionTotal2);
        examTicketTotal2.addQuestion(questionTotal1);
        examTicketTotal2.addQuestion(questionTotal2);
        orderDB = orderRepository.save(orderDB);
        assertNotNull(orderDB.getId());

        QuestionDB questionSpec1 = getQuestion(TestTypes.SPEC_TEST, DEFAULT_NDT_METHOD);
        questionSpec1.getAnswerVariants().addAll(getAnswers(4));
        questionSpec1 = questionRepository.save(questionSpec1);
        QuestionDB questionSpec2 = getQuestion(TestTypes.SPEC_TEST, DEFAULT_NDT_METHOD);
        questionSpec2.getAnswerVariants().addAll(getAnswers(4));
        questionSpec2 = questionRepository.save(questionSpec2);
        ExamTicketDB examTicketSpec1 = getExamTicket();
        ExamTicketDB examTicketSpec2 = getExamTicket();
        examSpec.addExamTicket(examTicketSpec1);
        examSpec.addExamTicket(examTicketSpec2);
        orderDB.addExam(examSpec);
        examTicketSpec1.addQuestion(questionSpec1);
        examTicketSpec1.addQuestion(questionSpec2);
        examTicketSpec2.addQuestion(questionSpec1);
        examTicketSpec2.addQuestion(questionSpec2);
        orderDB = orderRepository.save(orderDB);
        assertEquals(2, orderDB.getExams().size());

        QuestionDB questionSpec6_1 = getQuestion(TestTypes.SPEC_6_SECTOR_TEST, DEFAULT_NDT_METHOD);
        questionSpec6_1.getAnswerVariants().addAll(getAnswers(4));
        questionSpec6_1 = questionRepository.save(questionSpec6_1);
        QuestionDB questionSpec6_2 = getQuestion(TestTypes.SPEC_6_SECTOR_TEST, DEFAULT_NDT_METHOD);
        questionSpec6_2.getAnswerVariants().addAll(getAnswers(4));
        questionSpec6_2 = questionRepository.save(questionSpec6_2);
        ExamTicketDB examTicketSpec6_1 = getExamTicket();
        ExamTicketDB examTicketSpec6_2 = getExamTicket();
        examSpec_6.addExamTicket(examTicketSpec6_1);
        examSpec_6.addExamTicket(examTicketSpec6_2);
        orderDB.addExam(examSpec_6);
        examTicketSpec6_1.addQuestion(questionSpec6_1);
        examTicketSpec6_1.addQuestion(questionSpec6_2);
        examTicketSpec6_2.addQuestion(questionSpec6_1);
        examTicketSpec6_2.addQuestion(questionSpec6_2);
        orderDB = orderRepository.save(orderDB);
        assertEquals(3, orderDB.getExams().size());

        int answersCount = orderDB.getExams().get(orderDB.getExams().indexOf(examTotal)).getTickets()
                .stream().filter(Predicate.isEqual(examTicketTotal1)).findFirst().get().getQuestions().stream().findFirst().get().getAnswerVariants().size();
        assertEquals(4,answersCount);

    }
}
