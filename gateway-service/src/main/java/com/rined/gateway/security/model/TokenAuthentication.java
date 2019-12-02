package com.rined.gateway.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public final class TokenAuthentication extends AbstractAuthenticationToken {
    private final String token;
    private final User principal;

    public TokenAuthentication(String token) {
        super(Collections.emptyList());
        this.token = token;
        this.principal = null;
        setAuthenticated(false);
    }

    public TokenAuthentication(String token,
                               User principal,
                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public User getPrincipal() {
        return principal;
    }

    @Override
    public Optional<String> getCredentials() {
        return Optional.empty();
    }

    public Optional<String> getToken() {
        return Optional.ofNullable(token);
    }
}
