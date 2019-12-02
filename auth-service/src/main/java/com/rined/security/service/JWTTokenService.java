package com.rined.security.service;

import com.rined.security.model.User;
import com.rined.security.properties.JWTProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JWTTokenService implements TokenService {

    private final JWTProperties properties;

    @Override
    public String transform(User user) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getUsername())
                .setExpiration(generateExpireTime())
                .claim("userId", user.getId())
                .claim("roles", user.getRoles())
                .claim("email", user.getEmail())
                .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                .compact();
    }

    private Date generateExpireTime() {
        return new Date(System.currentTimeMillis() + properties.getExpire());
    }
}
