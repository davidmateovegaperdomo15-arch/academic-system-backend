package com.taller.student.application;

import com.taller.student.domain.model.Student;
import com.taller.student.domain.repository.StudentRepository;
import com.taller.student.dto.StudentDTO;
import com.taller.student.dto.StudentResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class StudentService {
    @Inject
    StudentRepository repository;

    public StudentResponseDTO createStudent(StudentDTO dto) {
        Student student = new Student(null, dto.name, dto.email);
        Student saved = repository.save(student);

        StudentResponseDTO response = new StudentResponseDTO();
        response.id = saved.getId();
        response.name = saved.getName();
        response.email = saved.getEmail();
        response.averageGrade = saved.calculateAverage();
        return response;
    }
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}