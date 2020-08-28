package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.persistence.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

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

    private String number;

    private LocalDate date;


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
        return Objects.equals(getId(), orderDB.getId()) &&
                getNdtMethod() == orderDB.getNdtMethod() &&
                Objects.equals(getNumber(), orderDB.getNumber()) &&
                Objects.equals(getDate(), orderDB.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNdtMethod(), getNumber(), getDate());
    }
}
