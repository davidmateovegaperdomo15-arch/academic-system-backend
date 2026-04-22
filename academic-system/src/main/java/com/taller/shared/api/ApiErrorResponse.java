package com.taller.shared.api;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Standard JSON body for API errors (4xx/5xx).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

  public String code;
  public int status;
  public String message;
  public String path;
  public String timestamp;

  public ApiErrorResponse() {}

  public ApiErrorResponse(String code, int status, String message, String path, String timestamp) {
    this.code = code;
    this.status = status;
    this.message = message;
    this.path = path;
    this.timestamp = timestamp;
  }
}
