package com.lvg.tcreator;

import com.lvg.tcreator.persistence.repositories.ExamRepository;
import com.lvg.tcreator.persistence.repositories.OrderRepository;
import com.lvg.tcreator.persistence.repositories.QuestionRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations="classpath:test.properties")
public abstract class GenericTest {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ExamRepository examRepository;

    @Before
    public void deleteAllQuestionsFromDB(){
        questionRepository.deleteAll();
        examRepository.deleteAll();
        orderRepository.deleteAll();
    }


}
