package com.lvg.tcreator.models;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Victor Levchenko LVG Corp. on 21.04.2020.
 */

public class Statistic {
    private Order order;
    private List<ExamTicket> examTickets;

    public Statistic(Order order, List<ExamTicket> examTickets){
        this.order = order;
        this.examTickets = examTickets;
    }

    public Order getOrder() {
        return order;
    }

    public List<ExamTicket> getExamTickets() {
        return examTickets;
    }

    public static class ExamTicket {
        private TestTypes testTypes;
        private int number;
        private Set<Question> questions = new TreeSet<>();

        public ExamTicket(TestTypes testTypes){
            this.testTypes = testTypes;
            this.number = 1;
        }

        public TestTypes getTestTypes() {
            return testTypes;
        }

        public Set<Question> getQuestions() {
            return questions;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }




}
