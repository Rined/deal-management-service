package com.rined.gateway.security.tokens;

import com.rined.gateway.security.model.TokenAuthentication;

public interface TokenService {

    TokenObject transform(String token);

    boolean validate(TokenObject tokenObject);

    TokenAuthentication extractAuthentication(TokenObject tokenObject);

}
