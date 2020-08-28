package com.lvg.tcreator.models;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.AnswerVariantDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.services.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class QuestionServiceTest {

    @Autowired
    QuestionService service;

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

}