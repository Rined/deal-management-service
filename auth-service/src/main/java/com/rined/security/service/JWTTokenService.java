package com.rined.security.service;

import com.rined.security.model.User;
import com.rined.security.properties.JWTProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JWTTokenService implements TokenService {

    private final JWTProperties properties;

    @Override
    public String transform(User user) {
        return Jwts.builder()
                .setId(user.getId())
                .setSubject(user.getUsername())
                .setExpiration(generateExpireTime())
                .claim("roles", user.getRoles())
                .claim("email", user.getEmail())
                .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                .compact();
    }

    private Date generateExpireTime() {
        return new Date(System.currentTimeMillis() + properties.getExpire());
    }
}
