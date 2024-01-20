package com.shhrrtnvr.smartaquaculture.controller.error;

import com.shhrrtnvr.smartaquaculture.io.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionController {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> requestException(Exception e) {
    return ResponseEntity.badRequest().body(new ErrorResponse().setErrorMessage(e.getMessage()));
  }
}
