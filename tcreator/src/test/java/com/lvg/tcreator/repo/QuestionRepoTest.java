package com.lvg.tcreator.repo;

import com.lvg.tcreator.GenericTest;

import com.lvg.tcreator.persistence.models.AnswerVariantDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.repositories.QuestionRepository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;


public class QuestionRepoTest extends GenericTest {

    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void saveQuestionTest(){
        QuestionDB questionDB = ModelGenerator.getQuestion();
        questionDB.getAnswerVariants().addAll(ModelGenerator.getAnswers(4));
        questionDB = questionRepository.save(questionDB);
        Assert.assertNotNull(questionDB.getId());
        List<AnswerVariantDB> answerVariantDBS = questionDB.getAnswerVariants();
        Assert.assertEquals(4,answerVariantDBS.size());
        AnswerVariantDB answerVariantDB = answerVariantDBS.get(0);
        answerVariantDB.setCorrect(true);
        questionDB = questionRepository.save(questionDB);
        Assert.assertTrue(questionDB.getAnswerVariants().contains(answerVariantDB));
        questionDB.getAnswerVariants().remove(answerVariantDB);
        questionDB = questionRepository.save(questionDB);
        Assert.assertEquals(3,questionDB.getAnswerVariants().size());


    }


}
