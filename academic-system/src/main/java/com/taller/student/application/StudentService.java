package com.taller.student.application;

import com.taller.student.domain.model.Student;
import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;

import java.util.List;
import java.util.Map;

public interface StudentService{
  StudentResponseDTO createStudent(StudentDTO dto);

  List<StudentResponseDTO> getAllStudents();
  StudentResponseDTO getStudentById(Long id);
  void deleteStudentById(Long id);
  List<StudentResponseDTO> getApprovedStudents(Double passingGrade);
  Map<String, List<Double>> getGradesReportByStudent();

}