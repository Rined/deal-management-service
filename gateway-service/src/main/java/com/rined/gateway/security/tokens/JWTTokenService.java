package com.rined.gateway.security.tokens;

import com.rined.gateway.security.model.TokenAuthentication;
import com.rined.gateway.security.model.Role;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class JWTTokenService implements TokenService {

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
                Arrays.asList(Role.USER)
        );
    }
}
