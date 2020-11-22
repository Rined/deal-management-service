package com.rined.proposal.exception;

public class NotFoundException extends CustomMessageRuntimeException {
    public NotFoundException(String template, Object... args) {
        super(template, args);
    }
}
