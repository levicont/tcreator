package com.lvg.tcreator.converters;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.AnswerVariantDB;
import com.lvg.tcreator.persistence.models.QuestionDB;


public class QuestionModelConverter {

    public static QuestionDB getQuestionDB(Question question, NdtMethod method, TestTypes testType){
        QuestionDB questionDB = new QuestionDB();
        questionDB.setQuestionNumber(question.getNumber());
        questionDB.setQuestionText(getTextWithoutLeadNumber(question.getQuestionText()));
        questionDB.setTestTypes(testType);
        questionDB.setNdtMethod(method);
        question.getVariants().entrySet().forEach(variantEntry ->{
            AnswerVariantDB answerVariantDB = new AnswerVariantDB();
            answerVariantDB.setAnswerText(variantEntry.getKey());
            answerVariantDB.setCorrect(variantEntry.getValue());
            questionDB.getAnswerVariants().add(answerVariantDB);
        });

        return questionDB;
    }

    private static String getTextWithoutLeadNumber(String textWithLeadNumber){
        if (textWithLeadNumber.contains(". "))
            return textWithLeadNumber.substring(textWithLeadNumber.indexOf(". ")+2);
        else
            throw new TCreatorException("Incorrect format of question text. Text:"+textWithLeadNumber);

    }
}
