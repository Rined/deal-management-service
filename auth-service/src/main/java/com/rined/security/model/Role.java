package com.rined.security.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    PROVIDER, CONSUMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
