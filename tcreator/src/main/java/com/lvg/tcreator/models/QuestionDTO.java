package com.lvg.tcreator.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionDTO {

    private String text;
    private Integer number;
    private NdtMethod ndtMethod;
    private TestTypes testTypes;
    private final List<AnswerVariantDTO> answers = new ArrayList<>();

    public QuestionDTO(String text, Integer number, NdtMethod ndtMethod, TestTypes testTypes) {
        this.text = text;
        this.number = number;
        this.ndtMethod = ndtMethod;
        this.testTypes = testTypes;
    }

    public void addAnswerDTO(AnswerVariantDTO answerVariantDTO){
        answers.add(answerVariantDTO);
    }

    public void removeAnswerDTO(AnswerVariantDTO answerVariantDTO){
        answers.remove(answerVariantDTO);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public NdtMethod getNdtMethod() {
        return ndtMethod;
    }

    public void setNdtMethod(NdtMethod ndtMethod) {
        this.ndtMethod = ndtMethod;
    }

    public TestTypes getTestTypes() {
        return testTypes;
    }

    public void setTestTypes(TestTypes testTypes) {
        this.testTypes = testTypes;
    }

    public List<AnswerVariantDTO> getAnswers() {
        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO that = (QuestionDTO) o;
        return number.equals(that.number) && ndtMethod == that.ndtMethod && testTypes == that.testTypes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, ndtMethod, testTypes);
    }

    private static class AnswerVariantDTO {
        private String answerText;
        private Boolean correct = Boolean.FALSE;
        public AnswerVariantDTO(String answerText, Boolean correct){
            this.answerText = answerText;
            this.correct = correct;
        }

        public String getAnswerText() {
            return answerText;
        }

        public void setAnswerText(String answerText) {
            this.answerText = answerText;
        }

        public Boolean getCorrect() {
            return correct;
        }

        public void setCorrect(Boolean correct) {
            this.correct = correct;
        }
    }
}
