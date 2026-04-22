package com.taller.shared.exception;

import com.taller.common.enums.ErrorCode;
import com.taller.shared.api.ApiErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.Instant;

@Provider
public class IllegalStateExceptionMapper implements ExceptionMapper<IllegalStateException> {

  @Context
  UriInfo uriInfo;

  @Override
  public Response toResponse(IllegalStateException exception) {
    ErrorCode ec = ErrorCode.CONFLICT;
    ApiErrorResponse body =
        new ApiErrorResponse(
            ec.getCode(),
            ec.getHttpStatus(),
            exception.getMessage(),
            uriInfo != null ? uriInfo.getPath() : null,
            Instant.now().toString());
    return Response.status(ec.getHttpStatus()).entity(body).build();
  }
}
