package com.rined.gateway.security.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    PROVIDER, CONSUMER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
