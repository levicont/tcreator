package com.lvg.tcreator.validators;

import com.lvg.tcreator.GenericTest;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.services.QuestionService;
import com.lvg.tcreator.utils.Validator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;


@Transactional
public class ValidatorTest extends GenericTest{

    @Autowired
    private QuestionService questionDbService;
    @Autowired
    private com.lvg.tcreator.services.QuestionService questionModelService;

    @Autowired
    private Validator validator;

    @Test
    public void questionCountValidatorTest(){
        deleteAllQuestionsFromDB();
        questionModelService.storeAllQuestionsInDB();
        List<NdtMethod> incorrect = validator.validateQuestions();
        assertEquals(0, incorrect.size());
        List<QuestionDB> utQuestions = questionDbService.findByNdtMethod(NdtMethod.UT);
        utQuestions.forEach(question -> questionDbService.delete(question));
        incorrect = validator.validateQuestions();
        assertEquals(1,incorrect.size());
        assertEquals(NdtMethod.UT, incorrect.get(0));

    }


}
