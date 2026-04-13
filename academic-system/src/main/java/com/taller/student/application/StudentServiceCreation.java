package com.taller.student.application;

import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;

public interface StudentServiceCreation {
  StudentResponseDTO run(StudentDTO dto);
}
