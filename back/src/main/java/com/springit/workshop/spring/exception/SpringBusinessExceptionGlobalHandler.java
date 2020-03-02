package com.springit.workshop.spring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpringBusinessExceptionGlobalHandler {
    @ExceptionHandler({ SpringBusinessException.class })
    public final ResponseEntity<String> ResponseEntityhandleException(SpringBusinessException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
    }
}