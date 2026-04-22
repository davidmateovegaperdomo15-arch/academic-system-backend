package com.taller.shared.exception;

import com.taller.common.enums.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainExceptionTest {

  @Test
  void domainException_carriesCode() {
    DomainException ex = new DomainException(ErrorCode.CONFLICT, "msg");
    assertEquals(ErrorCode.CONFLICT, ex.getErrorCode());
    assertEquals("msg", ex.getMessage());
  }

  @Test
  void resourceNotFound_message() {
    ResourceNotFoundException ex = new ResourceNotFoundException("Student", 7L);
    assertEquals(ErrorCode.NOT_FOUND, ex.getErrorCode());
    assertTrue(ex.getMessage().contains("7"));
  }

  @Test
  void conflictException() {
    ConflictException ex = new ConflictException("dup");
    assertEquals(ErrorCode.CONFLICT, ex.getErrorCode());
    assertEquals("dup", ex.getMessage());
  }

  @Test
  void domainException_withCause() {
    Throwable cause = new IllegalStateException("root");
    DomainException ex = new DomainException(ErrorCode.INTERNAL_ERROR, "wrapped", cause);
    assertSame(cause, ex.getCause());
  }
}
