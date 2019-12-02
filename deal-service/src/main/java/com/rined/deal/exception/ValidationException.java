package com.rined.deal.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String template, Object... args) {
        this(String.format(template, args));
    }

    private ValidationException(String message) {
        super(message);
    }
}
