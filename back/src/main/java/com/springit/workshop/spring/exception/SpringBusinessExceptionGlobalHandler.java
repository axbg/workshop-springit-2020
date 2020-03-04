package com.springit.workshop.spring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class SpringBusinessExceptionGlobalHandler {
    @ExceptionHandler({SpringBusinessException.class})
    public final ResponseEntity<Map<String, String>> ResponseEntityhandleException(SpringBusinessException ex) {
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), ex.getHttpStatus());
    }
}