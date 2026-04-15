package com.taller.student.application.impl;

import com.taller.grade.domain.model.Grade;
import com.taller.grade.domain.service.GradeCalculator;
import com.taller.student.application.StudentService;
import com.taller.student.domain.model.Student;
import com.taller.student.domain.repository.StudentRepository;
import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;
import com.taller.student.infrastructure.mapper.StudentMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {
  private final StudentRepository repository;
  private final GradeCalculator gradeCalculator;
  //@inject implicito al parecer
  public StudentServiceImpl(@Named("Impl") StudentRepository repository,
                            GradeCalculator gradeCalculator) {
    this.repository = repository;
    this.gradeCalculator = gradeCalculator;
  }
  @Override
  public StudentResponseDTO createStudent(StudentDTO dto) {
    Student student = new Student(null, dto.name, dto.email);
    Student saved = repository.save(student);
    return StudentMapper.toResponseDTO(saved, gradeCalculator);
    //Tarea estudiar para pasarla a funcional a ver que sale
  }
  @Override
  //Check ahora mas rato que son las seis, que paja
  public List<StudentResponseDTO> getAllStudents() {
    return repository.findAll().stream()
      // Usamos una referencia a metodo para aplicar el Mapper a cada estudiante de la lista
      .map(student -> StudentMapper.toResponseDTO(student, gradeCalculator))
      .collect(Collectors.toList());
  }

  @Override
  public StudentResponseDTO getStudentById(Long id) {
    // TAREA: IF id es nullo pues hacer cosas preventivas. revisar parecidos al of Nullable pero no de optional
    // Buscamos, si no existe lanzamos error, si existe mapeamos
    Objects.requireNonNull(id, "El ID para buscar no puede ser nulo");
    if (id == null) {
      throw new IllegalArgumentException("No se puede buscar un estudiante sin proporcionar un ID válido.");
    }
    return repository.findById(id)
      .map(student -> StudentMapper.toResponseDTO(student, gradeCalculator))
      .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
  }

  @Override
  public void deleteStudentById(Long id) {
    repository.deleteById(id);

  }

  @Override
  public List<StudentResponseDTO> getApprovedStudents(Double passingGrade) {
    // 1. Convertimos la lista en un Stream
    return repository.findAll().stream()
      // 2. Filtramos (Lambda): Solo dejamos pasar a los que su promedio sea >= a passingGrade
      .filter(student -> student.calculateFinalGrade(gradeCalculator) >= passingGrade)
      // 3. Mapeamos (Lambda): Convertimos el objeto Student a StudentResponseDTO
      .map(student -> StudentMapper.toResponseDTO(student, gradeCalculator))
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
