package com.lvg.tcreator.repo;

import com.lvg.tcreator.GenericTest;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.repositories.ExamRepository;
import com.lvg.tcreator.persistence.repositories.OrderRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static com.lvg.tcreator.repo.ModelGenerator.getExamDB;
import static com.lvg.tcreator.repo.ModelGenerator.getOrderDB;
import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 29.01.2021.
 */
@Transactional
public class OrderRepoTest extends GenericTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ExamRepository examRepository;

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
    }
}
