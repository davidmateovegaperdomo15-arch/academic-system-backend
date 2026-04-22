package com.taller.student.application.usecase;

import java.util.function.Function; // <--- ¡La interfaz funcional de Java!

import com.taller.grade.domain.service.GradeCalculator;
import com.taller.student.domain.model.Student;
import com.taller.student.domain.repository.StudentRepository;
import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;
import com.taller.student.infrastructure.mapper.StudentMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
// Implementamos Function que recibe un StudentDTO y devuelve un StudentResponseDTO
public class CreateStudentUseCase2 implements Function<StudentDTO, StudentResponseDTO> {

  private final StudentRepository repository;
  private final GradeCalculator calculator;

  public CreateStudentUseCase2(@Named("Impl") StudentRepository repository, GradeCalculator calculator) {
    this.repository = repository;
    this.calculator = calculator;
  }

  @Override
  public StudentResponseDTO apply(StudentDTO dto) { // El único método de la interfaz
    Student student = new Student(null, dto.name, dto.email);
    Student saved = repository.save(student);
    return StudentMapper.toResponseDTO(saved, calculator);
  }
}