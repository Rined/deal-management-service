package com.rined.gateway.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Component
@RequiredArgsConstructor
@ConfigurationProperties("jwt")
public class JWTProperties {
    private final String secret;
}
