package com.taller.student.domain.repository;

import com.taller.student.domain.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);

    Optional<Student> findById(Long id);

    List<Student> findAll();

    void deleteById(Long id);
}