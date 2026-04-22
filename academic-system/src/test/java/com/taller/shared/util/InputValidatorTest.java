package com.taller.shared.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

  @Test
  void name_validatesAndTrims() {
    assertEquals("Juan Perez", InputValidator.sanitizeAndValidateName("  Juan   Perez  "));
  }

  @Test
  void name_invalid_throws() {
    assertThrows(IllegalArgumentException.class, () -> InputValidator.sanitizeAndValidateName(null));
    assertThrows(IllegalArgumentException.class, () -> InputValidator.sanitizeAndValidateName("x"));
    assertThrows(IllegalArgumentException.class, () -> InputValidator.sanitizeAndValidateName("123"));
  }

  @Test
  void email_normalizesAndValidates() {
    assertEquals("a@b.co", InputValidator.sanitizeAndValidateEmail("  A@B.CO  "));
  }

  @Test
  void email_invalid_throws() {
    assertThrows(IllegalArgumentException.class, () -> InputValidator.sanitizeAndValidateEmail("bad"));
    assertThrows(IllegalArgumentException.class, () -> InputValidator.sanitizeAndValidateEmail("a".repeat(300) + "@x.co"));
  }
}
