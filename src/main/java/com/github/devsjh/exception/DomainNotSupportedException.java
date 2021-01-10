package com.github.devsjh.exception;

public class DomainNotSupportedException extends BusinessException {

    public DomainNotSupportedException(String message) {
        super(message, ExceptionType.DOMAIN_NOT_SUPPORTED);
    }

    public DomainNotSupportedException() {
        super(ExceptionType.DOMAIN_NOT_SUPPORTED);
    }
}