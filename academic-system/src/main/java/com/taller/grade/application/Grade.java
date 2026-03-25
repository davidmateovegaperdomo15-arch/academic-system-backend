package com.taller.grade.application;

public class Grade {

    private Long id;
    private double value;

    public Grade(Long id, double value) {
        validateValue(value);
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    private void validateValue(double value) {
        if (value < 0.0 || value > 5.0) {
            throw new IllegalArgumentException("Grade must be between 0.0 and 5.0");
        }
    }
}