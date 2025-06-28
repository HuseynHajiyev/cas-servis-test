package com.cas.casdemo.casservis.exception;


import com.cas.casdemo.casservis.log.CustomLogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final CustomLogger logger;

    public GlobalExceptionHandler(CustomLogger logger) {
        this.logger = logger;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> ChandleBadRequestException(BadRequestException e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getMessage());
        logger.error("EXCEPTION", e.getMessage(), e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        logger.error("EXCEPTION", e.getMessage(), e);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Something went wrong. Please contact support.");
        logger.error("EXCEPTION", e.getMessage(), e);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
