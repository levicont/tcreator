package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.persistence.Constants;
import com.lvg.tcreator.persistence.repositories.ExamRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.lvg.tcreator.config.R.OrderProps.*;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Entity
@Table(name = "EXAM_ORDER")
public class OrderDB implements ModelDB {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NdtMethod ndtMethod;

    @Size(min=MIN_NUMBER_SIZE_VALUE, max= MAX_NUMBER_SIZE_VALUE, message= INVALID_NUMBER_SIZE_MESSAGE)
    private String number;

    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private final List<ExamDB> exams = new ArrayList<>();

    public void addExam(ExamDB examDB){
        exams.add(examDB);
        examDB.setOrder(this);
    }

    public void removeExam(ExamDB examDB){
        exams.remove(examDB);
        examDB.setOrder(null);
    }

    public List<ExamDB> getExams() {
        return exams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NdtMethod getNdtMethod() {
        return ndtMethod;
    }

    public void setNdtMethod(NdtMethod ndtMethod) {
        this.ndtMethod = ndtMethod;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDB orderDB = (OrderDB) o;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(number,orderDB.number);
        equalsBuilder.append(date, orderDB.date);
        equalsBuilder.append(ndtMethod, orderDB.ndtMethod);
        return equalsBuilder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        hashCodeBuilder.append(ndtMethod).append(number).append(date);
        return hashCodeBuilder.hashCode();
    }
}
