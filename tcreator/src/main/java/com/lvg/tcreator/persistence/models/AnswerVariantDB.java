package com.lvg.tcreator.persistence.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Embeddable
public class AnswerVariantDB implements ModelDB{

    @Column(columnDefinition = "text")
    private String text;
    private boolean correct;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
