package com.taller.shared.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** {@link ParameterizedTest} runs the same test once per data row (see each {@code *Source}). */
class InputValidatorParameterizedTest {

  @ParameterizedTest
  @ValueSource(
      strings = {
        "x",
        "1",
        "12",
        "No!Chars",
        "123456",
      })
  void name_rejectsValuesThatDoNotMatchPattern(String invalidName) {
    assertThrows(
        IllegalArgumentException.class, () -> InputValidator.sanitizeAndValidateName(invalidName));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   "})
  void name_rejectsNullOrBlank(String invalidName) {
    assertThrows(
        IllegalArgumentException.class, () -> InputValidator.sanitizeAndValidateName(invalidName));
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = '|',
      value = {
        "  Juan   Perez | Juan Perez",
        "AB|AB",
      })
  void name_trimsAndNormalizesInnerSpaces(String raw, String expected) {
    assertEquals(expected, InputValidator.sanitizeAndValidateName(raw));
  }

  @ParameterizedTest
  @MethodSource("validEmailCases")
  void email_trimsToLowerAndAccepts(String raw, String expectedEmail) {
    assertEquals(expectedEmail, InputValidator.sanitizeAndValidateEmail(raw));
  }

  static Stream<Arguments> validEmailCases() {
    return Stream.of(
        Arguments.of("  A@B.CO  ", "a@b.co"),
        Arguments.of("User@EXAMPLE.ORG", "user@example.org"));
  }
}
