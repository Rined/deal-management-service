package com.rined.gateway.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JWTException extends AuthenticationException {
    public JWTException(String msg, Throwable t) {
        super(msg, t);
    }
}
