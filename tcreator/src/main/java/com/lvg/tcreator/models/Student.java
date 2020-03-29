package com.lvg.tcreator.models;

import java.util.Objects;

/**
 * Created by Victor Levchenko LVG Corp. on 26.03.2020.
 */
public class Student {

    private String name;
    private String secondName;
    private String surname;
    private NdtMethod ndtMethod;
    private NdtQualificationLevel ndtQualificationLevel;
    private boolean isTotalTest;
    private boolean isSpecTest;
    private boolean is6sector;
    private boolean is7sector;
    private boolean is8sector;

    protected Student(){}

    public Student(String name, String secondName, String surname, NdtMethod ndtMethod, NdtQualificationLevel ndtQualificationLevel) {
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.ndtMethod = ndtMethod;
        this.ndtQualificationLevel = ndtQualificationLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getName(), student.getName()) &&
                Objects.equals(getSecondName(), student.getSecondName()) &&
                Objects.equals(getSurname(), student.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSecondName(), getSurname());
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + secondName + " "+
                ndtMethod + "-" + ndtQualificationLevel;
    }
}
