package com.taller.student.application;

import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;
@FunctionalInterface
public interface CreateStudentUseCase {
  StudentResponseDTO run(StudentDTO dto);
}
