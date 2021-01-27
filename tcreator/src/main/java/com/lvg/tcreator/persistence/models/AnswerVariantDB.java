package com.lvg.tcreator.persistence.models;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Embeddable
public class AnswerVariantDB implements ModelDB{

    @Column(columnDefinition = "text")
    private String answerText;
    private Boolean correct;


    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerVariantDB that = (AnswerVariantDB) o;

        if (correct != that.correct) return false;
        return answerText.equals(that.answerText);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(answerText).hashCode();
    }
}
