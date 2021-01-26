package com.lvg.tcreator.models;

import com.lvg.tcreator.persistence.models.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ModelsGenerator {

    public static Set<AnswerVariantDB> getAnswerVariants(){
        Set<AnswerVariantDB> result = new HashSet<>();
        AnswerVariantDB av0 = new AnswerVariantDB();
        av0.setText("a. Вариант 1");
        av0.setCorrect(false);
        result.add(av0);

        AnswerVariantDB av1 = new AnswerVariantDB();
        av1.setText("b. Вариант 2");
        av1.setCorrect(false);
        result.add(av1);

        AnswerVariantDB av2 = new AnswerVariantDB();
        av2.setText("c. Вариант 3");
        av2.setCorrect(false);
        result.add(av2);

        AnswerVariantDB av3 = new AnswerVariantDB();
        av3.setText("d. Вариант 4");
        av3.setCorrect(true);
        result.add(av3);

        return result;
    }

    public static QuestionDB getQuestionDB(){
        QuestionDB questionDB = new QuestionDB();

        questionDB.setNdtMethod(NdtMethod.UT);
        questionDB.setNumber(1);
        questionDB.setTestTypes(TestTypes.TOTAL_TEST);
        questionDB.setText("Question 1");
        questionDB.getAnswerVariants().addAll(getAnswerVariants());

        return questionDB;
    }

    public static ExamTicketDB getExamTicketDB(){
        ExamTicketDB ticket = new ExamTicketDB();
        QuestionDB q1 = getQuestionDB();
        q1.setText("Question 1");

        QuestionDB q2 = getQuestionDB();
        q2.setText("Question 2");

        QuestionDB q3 = getQuestionDB();
        q3.setText("Question 3");

        ticket.getQuestions().add(q1);
        ticket.getQuestions().add(q2);
        ticket.getQuestions().add(q3);


        return ticket;
    }

    public static ExamDB getExamDB(){
        ExamDB exam = new ExamDB();
        exam.setOrder(getOrderDB());
        exam.setTestTypes(TestTypes.TOTAL_TEST);
        exam.addExamTicket(getExamTicketDB());
        return exam;
    }

    public static OrderDB getOrderDB(){
        OrderDB order = new OrderDB();
        order.setDate(LocalDate.of(2020,11,25));
        order.setNdtMethod(NdtMethod.UT);
        order.setNumber("01/11");
        return order;
    }

    public static QuestionStatisticDB getQuestionStatisticDB(){
        QuestionStatisticDB questionStatisticDB = new QuestionStatisticDB();


        return questionStatisticDB;
    }







}
