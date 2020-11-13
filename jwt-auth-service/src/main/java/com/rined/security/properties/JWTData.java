package com.rined.security.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.KeyPair;

@Getter
@RequiredArgsConstructor
public class JWTData {
    private final KeyPair pair;
    private final JWTProperties properties;
}
