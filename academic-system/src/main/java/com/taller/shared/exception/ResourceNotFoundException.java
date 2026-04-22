package com.taller.shared.exception;

import com.taller.common.enums.ErrorCode;

public class ResourceNotFoundException extends DomainException {

  public ResourceNotFoundException(String resourceType, Object id) {
    super(ErrorCode.NOT_FOUND, resourceType + " with id " + id + " was not found");
  }
}
