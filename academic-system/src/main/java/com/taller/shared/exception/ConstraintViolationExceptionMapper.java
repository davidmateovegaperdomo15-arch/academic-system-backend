package com.taller.shared.exception;

import com.taller.common.enums.ErrorCode;
import com.taller.shared.api.ApiErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.Instant;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  @Context
  UriInfo uriInfo;

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    String message =
        exception.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining("; "));
    ErrorCode ec = ErrorCode.VALIDATION_ERROR;
    ApiErrorResponse body =
        new ApiErrorResponse(
            ec.getCode(),
            ec.getHttpStatus(),
            message,
            uriInfo != null ? uriInfo.getPath() : null,
            Instant.now().toString());
    return Response.status(ec.getHttpStatus()).entity(body).build();
  }
}
