package com.rined.security.service;

import com.rined.security.properties.JWTData;
import com.rined.security.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JWTTokenService implements TokenService {

    private final JWTData jwtData;

    @Override
    public String transform(User user) {
        return Jwts.builder()
                .setHeaderParam("kid", "dms-key-id")
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getUsername())
                .setExpiration(generateExpireTime())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .claim("email", user.getEmail())
                .signWith(SignatureAlgorithm.RS256, jwtData.getPair().getPrivate())
                .compact();
    }

    private Date generateExpireTime() {
        return new Date(System.currentTimeMillis() + jwtData.getProperties().getExpire());
    }
}
