package com.github.devsjh.exception;

import com.github.devsjh.model.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<?> handleBusinessException(final BusinessException e) {
        log.info("[ERROR]: {}", e.getMessage());
        final ExceptionType exceptionType = e.getExceptionType();
        final ExceptionResponse response = ExceptionResponse.of(exceptionType);
        return new ResponseEntity<>(response, HttpStatus.valueOf(exceptionType.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleException(Exception e) {
        log.info("[ERROR]: {}", e.getMessage());
        final ExceptionResponse response = ExceptionResponse.of(ExceptionType.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}