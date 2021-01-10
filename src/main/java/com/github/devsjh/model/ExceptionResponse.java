package com.github.devsjh.model;

import com.github.devsjh.exception.ExceptionType;
import lombok.Getter;

@Getter
public class ExceptionResponse {

    private final int status;
    private final String message;

    private ExceptionResponse(final ExceptionType type) {
        this.status = type.getStatus();
        this.message = type.getMessage();
    }

    public static ExceptionResponse of(final ExceptionType type) {
        return new ExceptionResponse(type);
    }
}