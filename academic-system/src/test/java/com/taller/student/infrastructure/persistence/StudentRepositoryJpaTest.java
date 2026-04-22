package com.taller.student.infrastructure.persistence;

import com.taller.student.domain.model.Student;
import com.taller.student.domain.repository.StudentRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StudentRepositoryJpaTest {

  @Inject
  @Named("Jpa")
  StudentRepository repository;

  @Test
  @Transactional
  void saveFindAllUpdateDelete() {
    Student created = repository.save(new Student(null, "Jpa User", "jpa@uni.edu"));
    assertNotNull(created.getId());

    Student updated = repository.save(new Student(created.getId(), "Jpa Updated", "new@uni.edu"));
    assertEquals(created.getId(), updated.getId());
    assertEquals("Jpa Updated", updated.getName());

    List<Student> all = repository.findAll();
    assertFalse(all.isEmpty());

    repository.deleteById(created.getId());
    assertTrue(repository.findById(created.getId()).isEmpty());
  }

  @Test
  @Transactional
  void findById_missing_returnsEmpty() {
    assertTrue(repository.findById(999_999L).isEmpty());
  }

  @Test
  @Transactional
  void deleteById_missing_noOp() {
    repository.deleteById(999_998L);
  }
}
