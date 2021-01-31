package com.lvg.tcreator.repo;

import com.lvg.tcreator.GenericTest;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.persistence.models.AnswerVariantDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.repositories.QuestionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lvg.tcreator.repo.ModelGenerator.getAnswers;
import static com.lvg.tcreator.repo.ModelGenerator.getQuestion;
import static org.junit.Assert.*;

@Transactional
public class QuestionRepoTest extends GenericTest {

    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void saveQuestionTest(){
        QuestionDB questionDB = getQuestion();
        questionDB.getAnswerVariants().addAll(getAnswers(4));
        questionDB = questionRepository.save(questionDB);
        assertNotNull(questionDB.getId());
        List<AnswerVariantDB> answerVariantDBS = questionDB.getAnswerVariants();
        assertEquals(4,answerVariantDBS.size());
        AnswerVariantDB answerVariantDB = answerVariantDBS.get(0);
        answerVariantDB.setCorrect(true);
        questionDB = questionRepository.save(questionDB);
        assertTrue(questionDB.getAnswerVariants().contains(answerVariantDB));
        questionDB.getAnswerVariants().remove(answerVariantDB);
        questionDB = questionRepository.save(questionDB);
        assertEquals(3,questionDB.getAnswerVariants().size());
    }

    @Test
    public void updateQuestionTest(){
        QuestionDB questionDB = getQuestion();
        questionDB = questionRepository.save(questionDB);
        assertNotNull(questionDB.getId());
        questionDB.setNdtMethod(NdtMethod.UT);
        questionDB = questionRepository.save(questionDB);
        assertEquals(NdtMethod.UT, questionDB.getNdtMethod());
    }

    @Test
    public void deleteQuestionTest(){
        QuestionDB questionDB = getQuestion();
        questionDB = questionRepository.save(questionDB);
        long countBeforeDelete = questionRepository.count();
        assertNotNull(questionDB.getId());
        questionRepository.delete(questionDB);
        Long countAfterDelete = questionRepository.count();
        assertEquals(Long.valueOf(countBeforeDelete-1), countAfterDelete);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void numberMethodTestTypeConstraintTest(){
        QuestionDB questionDB1 = getQuestion();
        questionDB1 = questionRepository.saveAndFlush(questionDB1);
        assertNotNull(questionDB1.getId());
        QuestionDB questionDB2 = getQuestion();
        questionDB2.setQuestionNumber(questionDB1.getQuestionNumber());
        questionRepository.saveAndFlush(questionDB2);
    }


}
