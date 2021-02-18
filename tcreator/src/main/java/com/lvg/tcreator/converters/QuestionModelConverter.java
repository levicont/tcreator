package com.lvg.tcreator.converters;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.QuestionDTO;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.AnswerVariantDB;
import com.lvg.tcreator.persistence.models.QuestionDB;


public class QuestionModelConverter {


    public static QuestionDB getQuestionDB(QuestionDTO questionDTO, NdtMethod method, TestTypes testType){
        QuestionDB questionDB = new QuestionDB();
        questionDB.setQuestionNumber(questionDTO.getNumber());
        questionDB.setQuestionText(questionDTO.getText());
        questionDB.setTestTypes(testType);
        questionDB.setNdtMethod(method);

        questionDTO.getAnswers().forEach((variantDTO) -> {
            AnswerVariantDB answerVariantDB = new AnswerVariantDB();
            answerVariantDB.setAnswerText(variantDTO.getAnswerText());
            answerVariantDB.setCorrect(variantDTO.isCorrect());
            questionDB.getAnswerVariants().add(answerVariantDB);
        });

        return questionDB;
    }


}
