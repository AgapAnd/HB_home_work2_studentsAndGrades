package ru.agapov.HW2;

import java.util.Map;

public class Student {
    private final String name;
    private Map<String, Integer> grades;

    public Student (String name) {
        this.name = name;
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
}
