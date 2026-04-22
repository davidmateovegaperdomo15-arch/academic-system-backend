package com.taller.student.infrastructure.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentResponseDTOBuilderTest {

  @Test
  void builder_setsFields() {
    StudentResponseDTO dto =
        new StudentResponseDTO.Builder()
            .id(5L)
            .name("Builder Name")
            .email("b@uni.edu")
            .averageGrade(4.2)
            .build();
    assertEquals(5L, dto.id);
    assertEquals("Builder Name", dto.name);
    assertEquals("b@uni.edu", dto.email);
    assertEquals(4.2, dto.averageGrade);
  }
}
