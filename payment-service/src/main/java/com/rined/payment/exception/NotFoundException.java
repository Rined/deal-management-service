package com.rined.payment.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String format, Object... data) {
        super(String.format(format, data));
    }
}
