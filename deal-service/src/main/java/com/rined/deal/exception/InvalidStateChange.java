package com.rined.deal.exception;

public class InvalidStateChange extends RuntimeException {
    public InvalidStateChange(String template, Object... args) {
        this(String.format(template, args));
    }

    private InvalidStateChange(String message) {
        super(message);
    }
}
