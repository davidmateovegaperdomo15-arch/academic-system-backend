package com.taller.grade.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

  @Test
  void value_mustBeInRange() {
    assertThrows(IllegalArgumentException.class, () -> new Grade(1L, -0.1));
    assertThrows(IllegalArgumentException.class, () -> new Grade(1L, 5.1));
    Grade g = new Grade(1L, 4.5);
    assertEquals(4.5, g.getValue());
    assertNull(g.getStudentId());
  }
}
