package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.Constants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Entity
@Table(name = "QUESTION",
        uniqueConstraints = @UniqueConstraint(columnNames = {"question_number","ndt_method","test_type"}))
public class QuestionDB implements ModelDB {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Column(name = "question_number",length = 500,nullable = false)
    private Integer questionNumber;

    @Column(name = "question_text", columnDefinition = "text")
    private String questionText;

    private Boolean enabled = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    @Column(name = "ndt_method",length = 20,nullable = false)
    private NdtMethod ndtMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_type",length = 20,nullable = false)
    private TestTypes testTypes;

    @ElementCollection
    @CollectionTable(name = "ANSWER_VARIANT", joinColumns = @JoinColumn(name = "QUESTION_ID"))
    private final List<AnswerVariantDB> answerVariants = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<AnswerVariantDB> getAnswerVariants() {
        return answerVariants;
    }

    public NdtMethod getNdtMethod() {
        return ndtMethod;
    }

    public TestTypes getTestTypes() {
        return testTypes;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
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

        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
