package com.github.devsjh.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"),
    NO_DATA_FOUND(HttpStatus.NOT_FOUND.value(), "No Data Found"),
    DOMAIN_NOT_SUPPORTED(HttpStatus.NOT_FOUND.value(), "Domain Not Supported");

    private final int status;
    private final String message;

    ExceptionType(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}