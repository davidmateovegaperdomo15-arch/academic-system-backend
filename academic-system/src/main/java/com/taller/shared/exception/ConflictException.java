package com.taller.shared.exception;

import com.taller.common.enums.ErrorCode;

public class ConflictException extends DomainException {

  public ConflictException(String message) {
    super(ErrorCode.CONFLICT, message);
  }
}
