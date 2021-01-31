package com.lvg.tcreator.repo;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class ModelGenerator {
    private static Integer questionNumber = 1;
    private static final String[] NUMBERS = new String[]{"a","b","c","d","e","f","g"};

    private static Integer getQuestionNumber(){
        return questionNumber++;
    }

    public static QuestionDB getQuestion(){
        Integer questionNumber = getQuestionNumber();
        QuestionDB questionDB = new QuestionDB();
        questionDB.setQuestionText("Some text of question " + questionNumber);
        questionDB.setQuestionNumber(questionNumber);
        questionDB.setEnabled(Boolean.TRUE);
        questionDB.setNdtMethod(NdtMethod.VT);
        questionDB.setTestTypes(TestTypes.TOTAL_TEST);
        return questionDB;
    }

    public static AnswerVariantDB getAnswerVariant(){
        AnswerVariantDB answerVariantDB = new AnswerVariantDB();
        answerVariantDB.setAnswerText("Answer variant");
        answerVariantDB.setCorrect(Boolean.FALSE);
        return answerVariantDB;
    }

    public static List<AnswerVariantDB> getAnswers(int count){
        List<AnswerVariantDB> result = new ArrayList<>();
        for (int i = 0; i <count; i++){
            AnswerVariantDB answer = getAnswerVariant();
            StringBuilder text = new StringBuilder();
            text.append(NUMBERS[i]).append(". ").append(answer.getAnswerText()).append(" ").append(i+1);
            answer.setAnswerText(text.toString());
            result.add(answer);
        }
        return result;
    }

    public static OrderDB getOrderDB(){
        OrderDB orderDB = new OrderDB();
        orderDB.setDate(LocalDate.of(2021,01,30));
        orderDB.setNumber("01/05");
        orderDB.setNdtMethod(NdtMethod.VT);
        return orderDB;
    }

    public static ExamDB getExamDB(){
        ExamDB examDB = new ExamDB();
        examDB.setTestTypes(TestTypes.TOTAL_TEST);
        return examDB;
    }

    public static ExamTicketDB getExamTicket() {
        ExamTicketDB examTicketDB = new ExamTicketDB();
        examTicketDB.setTicketVariant(1);
        return examTicketDB;
    }
}
