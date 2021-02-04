package com.lvg.tcreator.converters;

import com.lvg.tcreator.exceptions.TCreatorException;
import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Question;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.AnswerVariantDB;
import com.lvg.tcreator.persistence.models.QuestionDB;


public class QuestionModelConverter {
    private static final String QUESTION_NUMBER_SEPARATOR = ". ";

    public static QuestionDB getQuestionDB(Question question, NdtMethod method, TestTypes testType){
        QuestionDB questionDB = new QuestionDB();
        questionDB.setQuestionNumber(question.getNumber());
        questionDB.setQuestionText(getTextWithoutLeadNumber(question.getQuestionText()));
        questionDB.setTestTypes(testType);
        questionDB.setNdtMethod(method);
        question.getVariants().forEach((key, value) -> {
            AnswerVariantDB answerVariantDB = new AnswerVariantDB();
            answerVariantDB.setAnswerText(key);
            answerVariantDB.setCorrect(value);
            questionDB.getAnswerVariants().add(answerVariantDB);
        });

        return questionDB;
    }

    private static String getTextWithoutLeadNumber(String textWithLeadNumber){
        if (textWithLeadNumber.contains(QUESTION_NUMBER_SEPARATOR))
            return textWithLeadNumber.substring(textWithLeadNumber.indexOf(QUESTION_NUMBER_SEPARATOR)+2);
        else
            throw new TCreatorException("Incorrect format of question text. Text:"+textWithLeadNumber);
    }
}
