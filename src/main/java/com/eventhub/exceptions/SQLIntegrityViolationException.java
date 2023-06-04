package com.eventhub.exceptions;

public class SQLIntegrityViolationException extends RuntimeException {

    private String message;

    public SQLIntegrityViolationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
