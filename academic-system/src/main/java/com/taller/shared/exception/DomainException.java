package com.taller.shared.exception;

import com.taller.common.enums.ErrorCode;

/** Base for business errors mapped to HTTP by {@code DomainExceptionMapper}. */
public class DomainException extends RuntimeException {

  private final ErrorCode errorCode;

  public DomainException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public DomainException(ErrorCode errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
