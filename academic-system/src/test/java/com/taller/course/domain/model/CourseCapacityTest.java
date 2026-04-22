package com.taller.course.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseCapacityTest {

  @Test
  void enroll_thirtyStudents_fillsCourse() {
    Course c = new Course(1L, "Full Course");
    for (int i = 1; i <= 30; i++) {
      c.enrollStudent((long) i);
    }
    assertThrows(IllegalStateException.class, () -> c.enrollStudent(99L));
  }
}
