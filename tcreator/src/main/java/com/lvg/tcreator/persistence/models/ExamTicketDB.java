package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.persistence.Constants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Entity
@Table(name = "EXAM_TICKET")
public class ExamTicketDB implements ModelDB{

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Column(name = "ticket_variant")
    private Integer ticketVariant;

    @ElementCollection
    private final Set<QuestionDB> questions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private ExamDB exam;

    public void addQuestion(QuestionDB questionDB){
        if (questionDB.getId() == null)
            throw new IllegalArgumentException("Question must be saved before adding to ExamTicket");
        if (questionDB.getNdtMethod().equals(exam.getOrder().getNdtMethod())
        && questionDB.getTestTypes().equals(exam.getTestTypes())){
            questions.add(questionDB);
            return;
        }
        throw new IllegalArgumentException("Question is not correct for this exam - NdtMethod or ExamType is wrong.");
    }

    public void removeQuestion(QuestionDB questionDB){
        questions.remove(questionDB);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<QuestionDB> getQuestions() {
        return questions;
    }

    public ExamDB getExam() {
        return exam;
    }

    public void setExam(ExamDB exam) {
        this.exam = exam;
    }

    public Integer getTicketVariant() {
        return ticketVariant;
    }

    public void setTicketVariant(Integer ticketVariant) {
        this.ticketVariant = ticketVariant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamTicketDB that = (ExamTicketDB) o;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
