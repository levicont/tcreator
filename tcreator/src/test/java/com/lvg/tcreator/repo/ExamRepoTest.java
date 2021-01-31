package com.lvg.tcreator.repo;

import com.lvg.tcreator.GenericTest;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.repositories.ExamRepository;
import com.lvg.tcreator.persistence.repositories.OrderRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import static com.lvg.tcreator.repo.ModelGenerator.getExamDB;
import static com.lvg.tcreator.repo.ModelGenerator.getOrderDB;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Victor Levchenko LVG Corp. on 30.01.2021.
 */
@Transactional
public class ExamRepoTest extends GenericTest {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void saveExamTest(){
        ExamDB examDB = getExamDB();
        OrderDB orderDB = getOrderDB();
        orderDB.addExam(examDB);
        orderDB = orderRepository.save(orderDB);
        assertNotNull(orderDB.getId());
        assertNotNull(examDB.getId());
        assertNotNull(examDB.getOrder());
        assertNotNull(examDB.getOrder().getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void cannotPersistWithoutOrderTest(){
        ExamDB examDB = getExamDB();
        examRepository.saveAndFlush(examDB);
    }

    @Test
    public void updateExamTest(){
        ExamDB examDB = getExamDB();
        OrderDB orderDB = getOrderDB();
        orderDB.addExam(examDB);
        orderDB = orderRepository.save(orderDB);
        assertNotNull(orderDB.getId());
        assertNotNull(examDB.getId());
        examDB.setTestTypes(TestTypes.SPEC_6_SECTOR_TEST);
        examDB = examRepository.save(examDB);
        assertNotNull(examDB.getId());
        assertEquals(TestTypes.SPEC_6_SECTOR_TEST,examDB.getTestTypes());
        assertEquals(TestTypes.SPEC_6_SECTOR_TEST, orderDB.getExams().get(0).getTestTypes());
    }
}
