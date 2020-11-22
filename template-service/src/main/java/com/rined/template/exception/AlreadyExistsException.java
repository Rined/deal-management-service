package com.rined.template.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String template, Object... args) {
        super(String.format(template, args));
    }
}
