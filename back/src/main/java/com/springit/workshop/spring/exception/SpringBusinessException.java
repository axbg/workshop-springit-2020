package com.springit.workshop.spring.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpringBusinessException extends Exception {
    private final String message;
    private final HttpStatus httpStatus;
}