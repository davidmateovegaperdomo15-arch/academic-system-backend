package com.taller.grade.domain.service;

import com.taller.grade.domain.model.Grade;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageCalculatorTest {

  private final AverageCalculator calculator = new AverageCalculator();

  @Test
  void emptyOrNull_returnsZero() {
    assertEquals(0.0, calculator.calculate(null));
    assertEquals(0.0, calculator.calculate(List.of()));
  }

  @Test
  void averagesValues() {
    double avg =
        calculator.calculate(
            List.of(new Grade(1L, 4.0), new Grade(2L, 2.0)));
    assertEquals(3.0, avg, 1e-9);
  }
}
