package com.taller.grade.domain.service;

import com.taller.grade.domain.model.Grade;
import java.util.List;

public interface GradeCalculator {
  double calculate(List<Grade> grades);
}