package com.rined.security.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String template, Object... args) {
        this(String.format(template, args));
    }

    private AlreadyExistsException(String message) {
        super(message);
    }
}
