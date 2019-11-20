package com.rined.gateway.security.model;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("unused")
public enum Role implements GrantedAuthority {
    PROVIDER, CONSUMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
