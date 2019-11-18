package com.rined.gateway.security;

import com.rined.gateway.security.model.TokenAuthentication;
import com.rined.gateway.security.tokens.TokenService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class TokenAuthenticationProvider implements AuthenticationProvider {
    private final TokenService tokenService;

    TokenAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        return tokenAuthentication.getToken()
                .filter(tokenService::validate)
                .map(tokenService::extractAuthentication)
                .orElse(tokenAuthentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (TokenAuthentication.class.isAssignableFrom(authentication));
    }
}
