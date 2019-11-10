package com.rined.auth.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
    public AlreadyExistsException(String template, Object ... args) {
        this(String.format(template, args));
    }
}
