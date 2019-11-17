package com.rined.gateway;

public interface TokenProvider {

    boolean validate(String token);

    TokenAuthentication extractAuthentication(String token);

}
