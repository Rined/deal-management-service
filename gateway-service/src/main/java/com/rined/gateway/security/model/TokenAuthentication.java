package com.rined.gateway.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public final class TokenAuthentication extends AbstractAuthenticationToken {
    private final String credentials;
    private final String principal;
    private final String token;

    public TokenAuthentication(String token) {
        super(Collections.emptyList());
        this.token = token;
        this.credentials = null;
        this.principal = null;
        setAuthenticated(false);
    }

    public TokenAuthentication(String token,
                               String credentials,
                               String principal,
                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.credentials = credentials;
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Optional<String> getCredentials() {
        return Optional.ofNullable(credentials);
    }

    @Override
    public Optional<String> getPrincipal() {
        return Optional.ofNullable(principal);
    }


    public Optional<String> getToken() {
        return Optional.ofNullable(token);
    }
}
