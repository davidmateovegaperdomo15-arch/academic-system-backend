package com.taller.student.application.impl;

import com.taller.grade.domain.model.Grade;
import com.taller.grade.domain.service.AverageCalculator;
import com.taller.shared.exception.ResourceNotFoundException;
import com.taller.student.application.usecase.impl.StudentServiceImpl;
import com.taller.student.domain.model.Student;
import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;
import com.taller.student.infrastructure.persistence.StudentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

  private StudentRepositoryImpl repo;
  private StudentServiceImpl service;

  @BeforeEach
  void setUp() {
    repo = new StudentRepositoryImpl();
    service = new StudentServiceImpl(repo, new AverageCalculator());
  }

  @Test
  void createAndFind_roundTrip() {
    StudentDTO dto = new StudentDTO();
    dto.name = "Carlos Ruiz";
    dto.email = "carlos@uni.edu";

    StudentResponseDTO created = service.createStudent(dto);
    assertNotNull(created.id);
    assertEquals("Carlos Ruiz", created.name);

    StudentResponseDTO found = service.getStudentById(created.id);
    assertEquals(created.id, found.id);
  }

  @Test
  void getStudentById_notFound_throws() {
    assertThrows(ResourceNotFoundException.class, () -> service.getStudentById(404L));
  }

  @Test
  void deleteStudentById_notFound_throws() {
    assertThrows(ResourceNotFoundException.class, () -> service.deleteStudentById(404L));
  }

  @Test
  void deleteStudentById_existing_removes() {
    StudentDTO dto = new StudentDTO();
    dto.name = "To Delete";
    dto.email = "del@uni.edu";
    StudentResponseDTO s = service.createStudent(dto);
    service.deleteStudentById(s.id);
    assertThrows(ResourceNotFoundException.class, () -> service.getStudentById(s.id));
  }

  @Test
  void getApprovedStudents_filtersByGrade() {
    StudentDTO a = new StudentDTO();
    a.name = "High A";
    a.email = "ha@uni.edu";
    StudentDTO b = new StudentDTO();
    b.name = "Low B";
    b.email = "lb@uni.edu";

    StudentResponseDTO sa = service.createStudent(a);
    StudentResponseDTO sb = service.createStudent(b);

    injectGrade(sa.id, 5.0);
    injectGrade(sb.id, 2.0);

    List<StudentResponseDTO> approved = service.getApprovedStudents(4.0);
    assertEquals(1, approved.size());
    assertEquals("High A", approved.get(0).name);
  }

  @Test
  void getGradesReportByStudent_buildsMap() {
    StudentDTO dto = new StudentDTO();
    dto.name = "Report User";
    dto.email = "rep@uni.edu";
    StudentResponseDTO s = service.createStudent(dto);
    injectGrade(s.id, 4.5);

    Map<String, List<Double>> report = service.getGradesReportByStudent();
    assertTrue(report.containsKey("Report User"));
    assertEquals(List.of(4.5), report.get("Report User"));
  }

  private void injectGrade(Long studentId, double value) {
    Student s = repo.findById(studentId).orElseThrow();
    s.addGrade(new Grade(1L, value));
    repo.save(s);
  }
}
