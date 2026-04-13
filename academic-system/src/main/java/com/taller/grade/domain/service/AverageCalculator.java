package com.taller.grade.domain.service;

import com.taller.grade.domain.model.Grade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jdk.jfr.Name;

import java.util.List;
@ApplicationScoped
public class AverageCalculator implements GradeCalculator {

  @Override
  public double calculate(List<Grade> grades) {
    if (grades == null || grades.isEmpty()) {
      return 0.0;
    }
    return grades.stream()
      .mapToDouble(Grade::getValue)
      .average()
      .orElse(0.0);
  }
}