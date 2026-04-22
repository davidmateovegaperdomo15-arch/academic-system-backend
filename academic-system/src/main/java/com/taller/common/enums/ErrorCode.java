package com.taller.common.enums;

/**
 * Stable API error codes paired with default HTTP status for REST responses.
 */
public enum ErrorCode {
  VALIDATION_ERROR(400, "VALIDATION_ERROR"),
  NOT_FOUND(404, "NOT_FOUND"),
  CONFLICT(409, "CONFLICT"),
  INTERNAL_ERROR(500, "INTERNAL_ERROR");

  private final int httpStatus;
  private final String code;

  ErrorCode(int httpStatus, String code) {
    this.httpStatus = httpStatus;
    this.code = code;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public String getCode() {
    return code;
  }
}
