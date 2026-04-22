package com.taller.student.infrastructure.mapper;

import com.taller.grade.domain.service.AverageCalculator;
import com.taller.student.domain.model.Student;
import com.taller.student.infrastructure.dto.StudentResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

  private final AverageCalculator calculator = new AverageCalculator();

  @Test
  void nullStudent_returnsNull() {
    assertNull(StudentMapper.toResponseDTO(null, calculator));
  }

  @Test
  void mapsFieldsAndAverage() {
    Student s = new Student(10L, "Maria Paz", "maria@uni.edu");
    StudentResponseDTO dto = StudentMapper.toResponseDTO(s, calculator);
    assertEquals(10L, dto.id);
    assertEquals("Maria Paz", dto.name);
    assertEquals("maria@uni.edu", dto.email);
    assertEquals(0.0, dto.averageGrade);
  }
}
