package com.lvg.tcreator.persistence.models;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.persistence.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Victor Levchenko LVG Corp. on 30.04.2020.
 */
@Entity
@Table(name = "EXAM_ORDER")
public class OrderDB implements Serializable {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NdtMethod ndtMethod;

    private String number;

    private LocalDate date;

    private Integer variantCount;
    private Boolean totalTestPresent;
    private Boolean specTestPresent;
    private Boolean spec6SectorPresent;
    private Boolean spec7SectorPresent;
    private Boolean spec8SectorPresent;

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

    public Integer getVariantCount() {
        return variantCount;
    }

    public void setVariantCount(Integer variantCount) {
        this.variantCount = variantCount;
    }

    public Boolean getTotalTestPresent() {
        return totalTestPresent;
    }

    public void setTotalTestPresent(Boolean totalTestPresent) {
        this.totalTestPresent = totalTestPresent;
    }

    public Boolean getSpecTestPresent() {
        return specTestPresent;
    }

    public void setSpecTestPresent(Boolean specTestPresent) {
        this.specTestPresent = specTestPresent;
    }

    public Boolean getSpec6SectorPresent() {
        return spec6SectorPresent;
    }

    public void setSpec6SectorPresent(Boolean spec6SectorPresent) {
        this.spec6SectorPresent = spec6SectorPresent;
    }

    public Boolean getSpec7SectorPresent() {
        return spec7SectorPresent;
    }

    public void setSpec7SectorPresent(Boolean spec7SectorPresent) {
        this.spec7SectorPresent = spec7SectorPresent;
    }

    public Boolean getSpec8SectorPresent() {
        return spec8SectorPresent;
    }

    public void setSpec8SectorPresent(Boolean spec8SectorPresent) {
        this.spec8SectorPresent = spec8SectorPresent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDB orderDB = (OrderDB) o;
        return Objects.equals(getId(), orderDB.getId()) &&
                getNdtMethod() == orderDB.getNdtMethod() &&
                Objects.equals(getNumber(), orderDB.getNumber()) &&
                Objects.equals(getDate(), orderDB.getDate()) &&
                Objects.equals(getVariantCount(), orderDB.getVariantCount()) &&
                Objects.equals(getTotalTestPresent(), orderDB.getTotalTestPresent()) &&
                Objects.equals(getSpecTestPresent(), orderDB.getSpecTestPresent()) &&
                Objects.equals(getSpec6SectorPresent(), orderDB.getSpec6SectorPresent()) &&
                Objects.equals(getSpec7SectorPresent(), orderDB.getSpec7SectorPresent()) &&
                Objects.equals(getSpec8SectorPresent(), orderDB.getSpec8SectorPresent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNdtMethod(), getNumber(), getDate(), getVariantCount(), getTotalTestPresent(), getSpecTestPresent(), getSpec6SectorPresent(), getSpec7SectorPresent(), getSpec8SectorPresent());
    }
}
