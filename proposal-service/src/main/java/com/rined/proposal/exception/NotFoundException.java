package com.rined.proposal.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String template, Object... args) {
        super(String.format(template, args));
    }
}
