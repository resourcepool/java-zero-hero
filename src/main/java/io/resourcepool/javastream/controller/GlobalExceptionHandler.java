package io.resourcepool.javastream.controller;

import io.resourcepool.javastream.controller.dtos.ErrorDto;
import io.resourcepool.javastream.exceptions.UserBlacklistedException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserBlacklistedException.class)
  public ResponseEntity<ErrorDto> handleUserBlacklistedException(UserBlacklistedException e) {
    return new ResponseEntity<>(ErrorDto.unauthorized(e.getMessage()), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    String fields = e.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(FieldError::getField)
      .collect(Collectors.joining(","));
    return new ResponseEntity<>(ErrorDto.badRequest("invalid value for fields:" + fields), BAD_REQUEST);
  }
}
