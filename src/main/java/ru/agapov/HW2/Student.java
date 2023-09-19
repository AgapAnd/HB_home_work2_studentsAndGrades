package ru.agapov.HW2;

import java.util.Map;
import java.util.Objects;

public class Student {
    private String name;
    private Map<String, Integer> grades;
    public Student() {};

    public void setName(String name) {
        this.name = name;
    }

    public Student (String name) {
        this.name = name;
    }
    public Student (String name, Map<String, Integer> grades) {
        this.name = name;
        this.grades = grades;
    }


    public String getName() {
        return name;
    }

    public Map<String,Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Ученик: " + name + "\nОценки: " + grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
