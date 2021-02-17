package com.lvg.tcreator.models;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ExamTicketDTO {

    private Integer ticketNumber;
    private TestTypes testTypes;
    private NdtMethod ndtMethod;
    private final Set<QuestionDTO> questionDTOSet = new TreeSet<>(Comparator.comparing(QuestionDTO::getNumber));

    public ExamTicketDTO(Integer ticketNumber, TestTypes testTypes, NdtMethod ndtMethod) {
        this.ticketNumber = ticketNumber;
        this.testTypes = testTypes;
        this.ndtMethod = ndtMethod;
    }

    public void addQuestionDTO(QuestionDTO questionDTO){
        if (questionDTO.getNdtMethod().equals(ndtMethod) && questionDTO.getTestTypes().equals(testTypes))
            questionDTOSet.add(questionDTO);
        throw new IllegalArgumentException("QuestionDTO and ExamTicketDTO have different ndtMethod or testType");
    }

    public void removeQuestionDTO(QuestionDTO questionDTO){
        questionDTOSet.remove(questionDTO);
    }

    public Set<QuestionDTO> getQuestionDTOSet() {
        return questionDTOSet;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public TestTypes getTestTypes() {
        return testTypes;
    }

    public void setTestTypes(TestTypes testTypes) {
        this.testTypes = testTypes;
    }

    public NdtMethod getNdtMethod() {
        return ndtMethod;
    }

    public void setNdtMethod(NdtMethod ndtMethod) {
        this.ndtMethod = ndtMethod;
    }
}

