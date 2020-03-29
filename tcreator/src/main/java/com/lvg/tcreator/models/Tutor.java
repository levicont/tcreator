package com.lvg.tcreator.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Victor Levchenko LVG Corp. on 26.03.2020.
 */
public class Tutor {

    private String name;
    private String secondName;
    private String surname;
    private Map<NdtMethod, NdtQualificationLevel> certificates = new HashMap<>();
    private TutorStatus tutorStatus;


    protected Tutor(){}

    public Tutor(String name, String secondName, String surname,
                 NdtMethod ndtMethod, NdtQualificationLevel ndtQualificationLevel, TutorStatus tutorStatus){
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.certificates.put(ndtMethod, ndtQualificationLevel);
        this.tutorStatus =tutorStatus;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }

    public Map<NdtMethod, NdtQualificationLevel> getCertificates() {
        return certificates;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return surname+" "+name+" "+secondName+" - "+certificates.toString() + "  status: "+tutorStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(getName(), tutor.getName()) &&
                Objects.equals(getSecondName(), tutor.getSecondName()) &&
                Objects.equals(getSurname(), tutor.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSecondName(), getSurname());
    }
}
