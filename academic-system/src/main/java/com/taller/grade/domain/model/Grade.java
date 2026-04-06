package com.taller.grade.domain.model;

public class Grade {

    private Long id;
    private Double value;
    private Long studentId;

    public Grade(Long id, Double value) {
        validateValue(value);
        if(studentId == null) {
            throw  new IllegalArgumentException("Student id is null");
        }
        this.id = id;
        this.value = value;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }
    public Long getStudentId() {return studentId;}
    public double getValue() {
        return value;
    }

    private void validateValue(double value) {
        if (value < 0.0 || value > 5.0) {
            throw new IllegalArgumentException("Grade must be between 0.0 and 5.0");
        }
    }
}