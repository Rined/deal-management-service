package com.rined.proposal.exception;

public abstract class CustomMessageRuntimeException extends RuntimeException {
    public CustomMessageRuntimeException(String template, Object... args) {
        super(String.format(template, args));
    }
}
