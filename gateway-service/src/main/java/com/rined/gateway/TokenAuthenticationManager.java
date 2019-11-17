package com.rined.gateway;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationManager implements AuthenticationManager {
    private final TokenProvider tokenProvider;

    public TokenAuthenticationManager(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.err.println("authenticate");
        if (authentication instanceof TokenAuthentication) {
            TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
            return tokenAuthentication.getToken()
                    .filter(tokenProvider::validate)
                    .map(tokenProvider::extractAuthentication)
                    .orElse(tokenAuthentication);

        } else return authentication;
    }
}
