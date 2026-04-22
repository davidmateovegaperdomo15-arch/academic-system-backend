package com.taller.common.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorCodeTest {

  @Test
  void accessors() {
    assertEquals(404, ErrorCode.NOT_FOUND.getHttpStatus());
    assertEquals("NOT_FOUND", ErrorCode.NOT_FOUND.getCode());
    assertEquals(400, ErrorCode.VALIDATION_ERROR.getHttpStatus());
    assertEquals(409, ErrorCode.CONFLICT.getHttpStatus());
    assertEquals(500, ErrorCode.INTERNAL_ERROR.getHttpStatus());
  }
}
