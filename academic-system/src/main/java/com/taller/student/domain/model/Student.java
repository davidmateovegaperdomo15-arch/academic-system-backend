package com.taller.student.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.taller.grade.domain.model.Grade;

public class Student {

    private Long id;
    private String name;
    private String email;
    private List<Grade> grades;

    public Student(Long id, String name, String email) {
        validateName(name);
        validateEmail(email);

        this.id = id;
        this.name = name;
        this.email = email;
        this.grades = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Grade> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    public void addGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null");
        }

        if (grades.size() >= 10) {
            throw new IllegalStateException("Max grades reached");
        }

        grades.add(grade);
    }

    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        return grades.stream()
                .mapToDouble(Grade::getValue)
                .average()
                .orElse(0.0);
    }

    public void updateName(String name) {
        validateName(name);
        this.name = name;
    }

    public void updateEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}