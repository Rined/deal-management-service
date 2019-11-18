package com.rined.gateway.security.tokens;

import com.rined.gateway.security.model.TokenAuthentication;

public interface TokenService {

    boolean validate(String token);

    TokenAuthentication extractAuthentication(String token);

}
