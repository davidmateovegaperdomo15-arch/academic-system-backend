package com.taller.course.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

  @Test
  void enrollStudent_rules() {
    Course c = new Course(1L, "Math");
    c.enrollStudent(10L);
    assertThrows(IllegalStateException.class, () -> c.enrollStudent(10L));
    assertThrows(IllegalArgumentException.class, () -> c.enrollStudent(null));
  }

  @Test
  void name_required() {
    assertThrows(IllegalArgumentException.class, () -> new Course(1L, "  "));
  }
}
