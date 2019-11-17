package com.rined.gateway;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TokenProviderImpl implements TokenProvider {
    @Override
    public boolean validate(String token) {
        return true;
    }

    @Override
    public TokenAuthentication extractAuthentication(String token) {
        return new TokenAuthentication(
                token,
                "credentials",
                "principal",
                Arrays.asList(Role.values())
        );
    }
}
