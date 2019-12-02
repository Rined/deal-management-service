package com.rined.deal.mail.exception;

public class TransformationNotFound extends RuntimeException {
    public TransformationNotFound(String template, Object... args) {
        this(String.format(template, args));
    }

    private TransformationNotFound(String message) {
        super(message);
    }
}
