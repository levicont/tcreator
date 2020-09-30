package com.lvg.tcreator;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class GenericTest {

    @PersistenceContext
    protected EntityManager em;

    protected void deleteAllQuestionsFromDB(){
        Query query = em.createQuery("delete from QuestionDB");
        query.executeUpdate();
    }
}
