package io.resourcepool.javastream.controller.dtos;

import java.time.Instant;

public record ErrorDto(int statusCode, String message, Instant occurredAt) {

  public static ErrorDto badRequest(String message) {
    return new ErrorDto(400, message, Instant.now());
  }

  public static ErrorDto unauthorized(String message) {
    return new ErrorDto(401, message, Instant.now());
  }

  public static ErrorDto forbidden(String message) {
    return new ErrorDto(403, message, Instant.now());
  }
}
