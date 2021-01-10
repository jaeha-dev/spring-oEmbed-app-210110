package com.github.devsjh.exception;

public class NoDataFoundException extends BusinessException {

    public NoDataFoundException(String message) {
        super(message, ExceptionType.NO_DATA_FOUND);
    }

    public NoDataFoundException() {
        super(ExceptionType.NO_DATA_FOUND);
    }
}