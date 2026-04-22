package com.taller.student.infrastructure.persistence;

import com.taller.student.domain.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryImplTest {

  private StudentRepositoryImpl repo;

  @BeforeEach
  void setUp() {
    repo = new StudentRepositoryImpl();
  }

  @Test
  void saveAssignsId_findAll_delete() {
    Student s = new Student(null, "Test User", "x@y.co");
    Student saved = repo.save(s);
    assertNotNull(saved.getId());
    assertEquals(1, repo.findAll().size());
    repo.deleteById(saved.getId());
    assertTrue(repo.findById(saved.getId()).isEmpty());
  }

  @Test
  void findById_missing_empty() {
    assertTrue(repo.findById(99L).isEmpty());
  }

  @Test
  void findAll_emptyInitially() {
    assertEquals(List.of(), repo.findAll());
  }
}
