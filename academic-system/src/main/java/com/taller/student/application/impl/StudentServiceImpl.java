package com.taller.student.application.impl;

import com.taller.grade.domain.model.Grade;
import com.taller.grade.domain.service.GradeCalculator;
import com.taller.student.application.StudentService;
import com.taller.student.domain.model.Student;
import com.taller.student.domain.repository.StudentRepository;
import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {
  private final StudentRepository repository;
  private final GradeCalculator gradeCalculator;

  @Override
  public StudentResponseDTO createStudent(StudentDTO dto) {
    Student student = new Student(null, dto.name, dto.email);
    Student saved = repository.save(student);
    StudentResponseDTO response = new StudentResponseDTO();
    response.id = saved.getId();
    response.name = saved.getName();
    response.email = saved.getEmail();
    response.averageGrade = saved.calculateFinalGrade(gradeCalculator);
    return response;
  }

  //@inject implicito al parecer

  public StudentServiceImpl(StudentRepository repository, GradeCalculator gradeCalculator) {
    this.repository = repository;
    this.gradeCalculator = gradeCalculator;
  }
  @Override
  //Check ahora mas rato que son las seis, que paja
  public List<StudentResponseDTO> getAllStudents() {
    return repository.findAll();
  }

  @Override
  public StudentResponseDTO getStudentById(Long id) {
    return null;
  }

  @Override
  public void deleteStudentById(Long id) {

  }

  @Override
  public List<StudentResponseDTO> getApprovedStudents(Double passingGrade) {
    // 1. Convertimos la lista en un Stream
    return repository.findAll().stream()
      // 2. Filtramos (Lambda): Solo dejamos pasar a los que su promedio sea >= a passingGrade
      .filter(student -> student.calculateFinalGrade(gradeCalculator) >= passingGrade)
      // 3. Mapeamos (Lambda): Convertimos el objeto Student a StudentResponseDTO
      .map(student -> {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.id = student.getId();
        dto.name = student.getName();
        dto.email = student.getEmail();
        dto.averageGrade = student.calculateFinalGrade(gradeCalculator);
        return dto;
      })
      // 4. Recolectamos el resultado de vuelta a una Lista
      .collect(Collectors.toList());
  }

  @Override
  public Map<String, List<Double>> getGradesReportByStudent() {
    return repository
      .findAll()
      .stream()
      // Collectors.toMap recibe dos lambdas:
      // La primera decide cuál será la LLAVE del mapa
      // La segunda decide cuál será el VALOR
      .collect(Collectors.toMap(
        Student::getName, // Llave: El nombre del estudiante
        student -> student.getGrades().stream() // Valor: Un stream interno de sus notas
          .map(Grade::getValue) // Sacamos solo el valor numérico
          .collect(Collectors.toList())
      ));
  }
}
