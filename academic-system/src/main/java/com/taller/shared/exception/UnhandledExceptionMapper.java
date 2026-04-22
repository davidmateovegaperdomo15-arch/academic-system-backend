package com.taller.shared.exception;

import com.taller.common.enums.ErrorCode;
import com.taller.shared.api.ApiErrorResponse;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.time.Instant;

/**
 * Fallback for unexpected errors; avoids leaking internals in the JSON message.
 */
@Provider
public class UnhandledExceptionMapper implements ExceptionMapper<Throwable> {

  private static final Logger LOG = Logger.getLogger(UnhandledExceptionMapper.class);

  @Context
  UriInfo uriInfo;

  @Override
  public Response toResponse(Throwable exception) {
    if (exception instanceof WebApplicationException wae) {
      return wae.getResponse();
    }
    LOG.error("Unhandled error", exception);
    ErrorCode ec = ErrorCode.INTERNAL_ERROR;
    ApiErrorResponse body =
        new ApiErrorResponse(
            ec.getCode(),
            ec.getHttpStatus(),
            "An unexpected error occurred",
            uriInfo != null ? uriInfo.getPath() : null,
            Instant.now().toString());
    return Response.status(ec.getHttpStatus()).entity(body).build();
  }
}
