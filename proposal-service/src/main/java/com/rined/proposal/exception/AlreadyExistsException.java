package com.rined.proposal.exception;

public class AlreadyExistsException extends CustomMessageRuntimeException {
    public AlreadyExistsException(String template, Object... args) {
        super(template, args);
    }
}
