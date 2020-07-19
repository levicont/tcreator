package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Entity
@Table(name = "QUESTION")
public class QuestionDB implements Serializable {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private Integer number;
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "ndt_method")
    private NdtMethod ndtMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_type")
    private TestTypes testTypes;

    @ElementCollection
    @CollectionTable(name = "ANSWER_VARIANT", joinColumns = @JoinColumn(name = "QUESTION_ID"))
    private Set<AnswerVariantDB> answerVariants = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public Set<AnswerVariantDB> getAnswerVariants() {
        return answerVariants;
    }

    public NdtMethod getNdtMethod() {
        return ndtMethod;
    }

    public TestTypes getTestTypes() {
        return testTypes;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNdtMethod(NdtMethod ndtMethod) {
        this.ndtMethod = ndtMethod;
    }

    public void setTestTypes(TestTypes testTypes) {
        this.testTypes = testTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDB that = (QuestionDB) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getText());
    }
}