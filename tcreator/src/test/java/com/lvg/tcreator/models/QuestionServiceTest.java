package com.lvg.tcreator.models;

import com.lvg.tcreator.GenericTest;
import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.AnswerVariantDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.services.QuestionService;
import com.lvg.tcreator.services.impl.QuestionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.net.www.content.text.Generic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


@Transactional
public class QuestionServiceTest extends GenericTest{


    @Autowired
    QuestionService service;
    @Autowired
    QuestionServiceImpl modelService;

    @Test
    public void saveQuestionTest() {
        QuestionDB question = ModelsGenerator.getQuestionDB();
        assertNull(question.getId());
        service.save(question);
        assertNotNull(question.getId());
    }

    @Test
    public void updateQuestionTest() {
        QuestionDB question = ModelsGenerator.getQuestionDB();
        service.save(question);

        Long id = question.getId();
        String oldText = question.getText();
        Integer oldNumber = question.getNumber();
        NdtMethod oldMethod = question.getNdtMethod();
        TestTypes oldTestType = question.getTestTypes();
        Set<AnswerVariantDB> oldAnswerVariants = question.getAnswerVariants();
        int oldAnswerVariantsCount = oldAnswerVariants.size();

        question.setText("New text");
        question.setNumber(1234);
        question.setNdtMethod(NdtMethod.RT);
        question.setTestTypes(TestTypes.SPEC_TEST);

        AnswerVariantDB newAnswerVariant = new AnswerVariantDB();
        newAnswerVariant.setText("New variant");
        newAnswerVariant.setCorrect(true);

        question.getAnswerVariants().add(newAnswerVariant);

        service.save(question);

        question = service.find(id);

        assertNotEquals(oldText, question.getText());
        assertNotEquals(oldNumber, question.getNumber());
        assertNotEquals(oldMethod, question.getNdtMethod());
        assertNotEquals(oldTestType, question.getTestTypes());

        assertNotEquals(oldAnswerVariantsCount, question.getAnswerVariants().size());
    }

    @Test(expected = TCreatorException.class)
    public void deleteQuestionTest(){
        QuestionDB question = ModelsGenerator.getQuestionDB();
        service.save(question);
        Long id = question.getId();
        assertNotNull(id);

        service.delete(question);

        service.find(id);
    }

    @Test
    public void countQuestionsTest(){
        List<QuestionDB> result = service.findAll();
        Long count = (long)result.size();
        assertEquals(count, (Long) service.count());
    }

    @Test
    public void storeAllQuestionsTest(){
        long count = service.count();
        modelService.storeAllQuestionsInDB();
        assertNotEquals(count, (long)service.findAll().size());
    }

    @Test
    public void findByNumberTestTypesNdtNumberTest() {
        deleteAllQuestionsFromDB();

        QuestionDB question = ModelsGenerator.getQuestionDB();
        assertNull(question.getId());
        service.save(question);
        assertNotNull(question.getId());
        QuestionDB questionDB = service.findByNumberTestTypesNdtMethod(question.getNumber(),
                question.getTestTypes(), question.getNdtMethod());
        assertEquals(question.getId(), questionDB.getId());
    }

    @Test
    public void findByNdtMethodTest(){
        deleteAllQuestionsFromDB();
        modelService.storeAllQuestionsInDB();
        List<QuestionDB> utQuestions = service.findByNdtMethod(NdtMethod.UT);
        assertNotEquals(0,utQuestions.size());
        Set<NdtMethod> methods = new HashSet<>();
        utQuestions.forEach(q -> methods.add(q.getNdtMethod()));

        assertEquals(1,methods.size());
    }






}