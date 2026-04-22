package com.taller.student.application.usecase;

import com.taller.grade.domain.service.AverageCalculator;
import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;
import com.taller.student.infrastructure.persistence.StudentRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateStudentUseCaseTest {

  @Test
  void apply_persistsAndMaps() {
    CreateStudentUseCase2 useCase =
        new CreateStudentUseCase2(new StudentRepositoryImpl(), new AverageCalculator());
    StudentDTO dto = new StudentDTO();
    dto.name = "Use Case User";
    dto.email = "uc@uni.edu";
    StudentResponseDTO out = useCase.apply(dto);
    assertNotNull(out.id);
    assertEquals("Use Case User", out.name);
    assertEquals("uc@uni.edu", out.email);
  }
}
