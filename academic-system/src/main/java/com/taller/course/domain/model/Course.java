package com.taller.course.domain.model;

import com.taller.student.domain.model.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {

    private Long id;
    private String name;
    private List<Long> enrolledStudentIds; // <--- Un curso contiene estudiantes

    public Course(Long id, String name) {
        validateName(name);
        this.id = id;
        this.name = name;
        this.enrolledStudentIds = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Long> getStudents() {
        return Collections.unmodifiableList(enrolledStudentIds);
    }

    public void enrollStudent(Long studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Cannot enroll a null student");
        }
        if (enrolledStudentIds.size() >= 30) { // Regla de negocio inventada
            throw new IllegalStateException("Course is full. Maximum 30 students allowed.");
        }
        // Evitar duplicados
        if (enrolledStudentIds.contains(studentId)) {
            throw new IllegalStateException("Student is already enrolled in this course");
        }

        enrolledStudentIds.add(studentId);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
    }
}