package com.rined.deal.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String template, Object... args) {
        this(String.format(template, args));
    }

    private NotFoundException(String message) {
        super(message);
    }
}
