package com.taller.student.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.taller.grade.domain.model.Grade;
import com.taller.grade.domain.service.GradeCalculator;
import com.taller.shared.domain.model.Person;

public class Student extends Person {

    private Long id;
    private List<Grade> grades;

    public Student(Long id, String name, String email) {
        super(name, email);

        this.id = id;
        this.grades = new ArrayList<>();
    }

    public Long getId() {
        return id;
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

    public double calculateFinalGrade(GradeCalculator calculator) {
        if (calculator == null) {
            throw new IllegalArgumentException("Calculator cannot be null");
        }
        // Le pasamos la lista inmutable de notas a la calculadora
        return calculator.calculate(this.grades);
    }

    @Override
    public boolean equals(Object o) {
        // 1. Si son exactamente el mismo espacio en memoria, son iguales
        if (this == o) return true;

        // 2. Si el otro objeto es nulo o ni siquiera es de la clase Student, no son iguales
        if (o == null || getClass() != o.getClass()) return false;

        // 3. Los comparamos por su ID (su identidad)
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        // Generamos el hash basados ÚNICAMENTE en el ID
        return Objects.hash(id);
    }
}