package com.taller.student.application.usecase.impl;

import com.taller.student.application.CreateStudentUseCase;
import com.taller.student.infrastructure.dto.StudentDTO;
import com.taller.student.infrastructure.dto.StudentResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateStudentUseCaseImpl implements CreateStudentUseCase {
  @Override
  public StudentResponseDTO run(StudentDTO dto) {

    return null;
  }
}
