package com.taller.shared.exception;

import com.taller.common.enums.ErrorCode;
import com.taller.shared.api.ApiErrorResponse;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionMappersTest {

  @Test
  void domainExceptionMapper_buildsBody() {
    DomainExceptionMapper mapper = new DomainExceptionMapper();
    Response r =
        mapper.toResponse(
            new DomainException(ErrorCode.NOT_FOUND, "missing"));
    assertEquals(404, r.getStatus());
    assertTrue(r.getEntity() instanceof ApiErrorResponse);
    ApiErrorResponse body = (ApiErrorResponse) r.getEntity();
    assertEquals("NOT_FOUND", body.code);
  }

  @Test
  void illegalArgumentMapper_maps400() {
    IllegalArgumentExceptionMapper mapper = new IllegalArgumentExceptionMapper();
    Response r = mapper.toResponse(new IllegalArgumentException("bad"));
    assertEquals(400, r.getStatus());
    assertEquals("VALIDATION_ERROR", ((ApiErrorResponse) r.getEntity()).code);
  }

  @Test
  void illegalStateMapper_maps409() {
    IllegalStateExceptionMapper mapper = new IllegalStateExceptionMapper();
    Response r = mapper.toResponse(new IllegalStateException("dup"));
    assertEquals(409, r.getStatus());
    assertEquals("CONFLICT", ((ApiErrorResponse) r.getEntity()).code);
  }

  @Test
  void unhandledMapper_runtime_returns500() {
    UnhandledExceptionMapper mapper = new UnhandledExceptionMapper();
    Response r = mapper.toResponse(new RuntimeException("internal"));
    assertEquals(500, r.getStatus());
    assertEquals("INTERNAL_ERROR", ((ApiErrorResponse) r.getEntity()).code);
  }

  @Test
  void unhandledMapper_webApplicationException_passthrough() {
    UnhandledExceptionMapper mapper = new UnhandledExceptionMapper();
    Response original = Response.status(418).build();
    Response r = mapper.toResponse(new WebApplicationException(original));
    assertEquals(418, r.getStatus());
  }

  @Test
  void constraintViolationMapper_emptyViolations() {
    ConstraintViolationExceptionMapper mapper = new ConstraintViolationExceptionMapper();
    Response r =
        mapper.toResponse(new ConstraintViolationException(Collections.emptySet()));
    assertEquals(400, r.getStatus());
    assertEquals("VALIDATION_ERROR", ((ApiErrorResponse) r.getEntity()).code);
  }
}
