package com.lvg.tcreator.models;

import com.lvg.tcreator.GenericTest;
import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.persistence.models.AnswerVariantDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.services.QuestionDBService;
import com.lvg.tcreator.services.QuestionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


@Transactional
public class QuestionServiceTest extends GenericTest{


    @Autowired
    QuestionDBService service;
    @Autowired
    QuestionService modelService;

    @Test
    public void saveQuestionTest() {
        QuestionDB question = GeneratorModels.getQuestionDB();
        assertNull(question.getId());
        service.save(question);
        assertNotNull(question.getId());
    }

    @Test
    public void updateQuestionTest() {
        QuestionDB question = GeneratorModels.getQuestionDB();
        service.save(question);

        Long id = question.getId();
        String oldText = question.getQuestionText();
        Integer oldNumber = question.getQuestionNumber();
        NdtMethod oldMethod = question.getNdtMethod();
        TestTypes oldTestType = question.getTestTypes();
        List<AnswerVariantDB> oldAnswerVariants = question.getAnswerVariants();
        int oldAnswerVariantsCount = oldAnswerVariants.size();

        question.setQuestionText("New text");
        question.setQuestionNumber(1234);
        question.setNdtMethod(NdtMethod.RT);
        question.setTestTypes(TestTypes.SPEC_TEST);

        AnswerVariantDB newAnswerVariant = new AnswerVariantDB();
        newAnswerVariant.setAnswerText("New variant");
        newAnswerVariant.setCorrect(true);

        question.getAnswerVariants().add(newAnswerVariant);

        service.save(question);

        question = service.find(id);

        assertNotEquals(oldText, question.getQuestionText());
        assertNotEquals(oldNumber, question.getQuestionNumber());
        assertNotEquals(oldMethod, question.getNdtMethod());
        assertNotEquals(oldTestType, question.getTestTypes());

        assertNotEquals(oldAnswerVariantsCount, question.getAnswerVariants().size());
    }

    @Test(expected = TCreatorException.class)
    public void deleteQuestionTest(){
        QuestionDB question = GeneratorModels.getQuestionDB();
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
        assertNotEquals(count, service.findAll().size());
    }

    @Test
    public void findByNumberTestTypesNdtNumberTest() {
        deleteAllQuestionsFromDB();

        QuestionDB question = GeneratorModels.getQuestionDB();
        assertNull(question.getId());
        service.save(question);
        assertNotNull(question.getId());
        QuestionDB questionDB = service.findByNumberTestTypesNdtMethod(question.getQuestionNumber(),
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