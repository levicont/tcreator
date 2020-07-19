package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.Constants;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Entity
@Table(name = "EXAM_TICKET")
public class ExamTicketDB {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_type")
    private TestTypes testTypes;

    private Integer number;

    @ElementCollection
    @CollectionTable(name = "exam_question", joinColumns = @JoinColumn(name = "exam_ticket_id"))
    @OrderBy("number")
    private Set<QuestionDB> questions = new LinkedHashSet<>();

    public TestTypes getTestTypes() {
        return testTypes;
    }

    public Integer getNumber() {
        return number;
    }

    public Set<QuestionDB> getQuestions() {
        return questions;
    }

    public void setTestTypes(TestTypes testTypes) {
        this.testTypes = testTypes;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamTicketDB that = (ExamTicketDB) o;
        return getTestTypes() == that.getTestTypes() &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getQuestions(), that.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTestTypes(), getNumber(), getQuestions());
    }
}
