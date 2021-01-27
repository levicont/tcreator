package com.lvg.tcreator.models;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.services.ExamService;
import com.lvg.tcreator.persistence.services.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ExamServiceTest {

    @Autowired
    private ExamService service;

    @Autowired
    private OrderService orderService;

    @Test
    public void saveExamTest(){
        ExamDB exam = GeneratorModels.getExamDB();
        Long id = exam.getId();
        assertNull(id);
        service.save(exam);
        assertNotNull(exam.getId());
    }

    @Test
    public void updateExamTest(){
        ExamDB exam = GeneratorModels.getExamDB();
        service.save(exam);
        Long id = exam.getId();
        TestTypes type = exam.getTestTypes();
        int ticketsCount = exam.getTickets().size();

        exam.setTestTypes(TestTypes.SPEC_6_SECTOR_TEST);
        exam.addExamTicket(GeneratorModels.getExamTicketDB());
        service.save(exam);

        ExamDB newExam = service.find(id);
        assertNotEquals(type, newExam.getTestTypes());
        assertNotEquals(ticketsCount, newExam.getTickets().size());

    }

    @Test(expected = TCreatorException.class)
    public void deleteExamTest(){
        ExamDB exam = GeneratorModels.getExamDB();
        service.save(exam);
        Long id  = exam.getId();
        assertNotNull(id);
        service.delete(exam);
        service.find(id);
    }

    @Test
    public void findExamsByOrderTest(){
        OrderDB order = GeneratorModels.getOrderDB();
        orderService.save(order);
        Long orderId = order.getId();

        ExamDB exam = GeneratorModels.getExamDB();
        exam.setOrder(order);
        service.save(exam);

        ExamDB exam2 = GeneratorModels.getExamDB();
        exam2.setTestTypes(TestTypes.SPEC_TEST);
        exam2.setOrder(order);
        service.save(exam2);

        List<ExamDB> exams = service.findByOrder(order);
        assertEquals(2, exams.size());

    }


}
