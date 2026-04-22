package com.taller.student.domain.model;

import com.taller.grade.domain.model.Grade;
import com.taller.grade.domain.service.AverageCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

  private final AverageCalculator calculator = new AverageCalculator();

  @Test
  void addGrade_rejectsNullAndCapsAtTen() {
    Student s = new Student(1L, "Test Name", "n@u.edu");
    assertThrows(IllegalArgumentException.class, () -> s.addGrade(null));
    for (int i = 0; i < 10; i++) {
      s.addGrade(new Grade((long) i, 3.0));
    }
    assertThrows(IllegalStateException.class, () -> s.addGrade(new Grade(99L, 3.0)));
  }

  @Test
  void calculateFinalGrade_requiresCalculator() {
    Student s = new Student(1L, "Test Name", "n@u.edu");
    assertThrows(IllegalArgumentException.class, () -> s.calculateFinalGrade(null));
  }

  @Test
  void personUpdateNameAndEmail() {
    Student s = new Student(1L, "Old", "old@u.edu");
    s.updateName("New Name");
    s.updateEmail("new@u.edu");
    assertEquals("New Name", s.getName());
    assertEquals("new@u.edu", s.getEmail());
  }

  @Test
  void equalsAndHashCode_byId() {
    Student a = new Student(1L, "Same Id", "a@u.edu");
    Student b = new Student(1L, "Other Name", "b@u.edu");
    assertEquals(a, b);
    assertEquals(a.hashCode(), b.hashCode());
    assertNotEquals(a, new Student(2L, "Same Id", "a@u.edu"));
  }
}
