package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.Constants;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Entity
@Table(name = "QUESTION")
public class QuestionDB implements ModelDB {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private Integer number;

    @Column(columnDefinition = "text")
    private String text;

    private Boolean enabled = Boolean.TRUE;

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

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionDB that = (QuestionDB) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(number, that.number)) return false;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(text).hashCode();
    }
}
