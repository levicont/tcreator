package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.Constants;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION_STATISTIC")
public class QuestionStatisticDB implements ModelDB{

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private Integer questionNumber;

    @Enumerated(EnumType.STRING)
    private TestTypes testType;

    @Enumerated(EnumType.STRING)
    private NdtMethod ndtMethod;

    @Column(nullable = false)
    private Long orderId;

    private Integer wrongAnswerCount;
    private Integer totalCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public TestTypes getTestType() {
        return testType;
    }

    public void setTestType(TestTypes testType) {
        this.testType = testType;
    }

    public NdtMethod getNdtMethod() {
        return ndtMethod;
    }

    public void setNdtMethod(NdtMethod ndtMethod) {
        this.ndtMethod = ndtMethod;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getWrongAnswerCount() {
        return wrongAnswerCount;
    }

    public void setWrongAnswerCount(Integer wrongAnswerCount) {
        this.wrongAnswerCount = wrongAnswerCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionStatisticDB that = (QuestionStatisticDB) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (questionNumber != null ? !questionNumber.equals(that.questionNumber) : that.questionNumber != null)
            return false;
        if (testType != that.testType) return false;
        if (ndtMethod != that.ndtMethod) return false;
        return orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (questionNumber != null ? questionNumber.hashCode() : 0);
        result = 31 * result + (testType != null ? testType.hashCode() : 0);
        result = 31 * result + (ndtMethod != null ? ndtMethod.hashCode() : 0);
        result = 31 * result + orderId.hashCode();
        return result;
    }
}
