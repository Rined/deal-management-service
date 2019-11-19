package com.rined.gateway.security.tokens;

import com.rined.gateway.properties.JWTProperties;
import com.rined.gateway.security.exception.JWTException;
import com.rined.gateway.security.model.Role;
import com.rined.gateway.security.model.TokenAuthentication;
import com.rined.gateway.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class JWTCheckTokenService implements TokenService {

    private final JWTProperties jwtProperties;

    @Override
    public TokenObject transform(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();

            return new TokenObject.TokenObjectBuilder()
                    .token(token)
                    .id(body.getId())
                    .username(body.getSubject())
                    .expire(body.getExpiration())
                    .email(String.valueOf(body.get("email")))
                    .roles(conversionToRole(body.get("roles", List.class)))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validate(TokenObject tokenObject) {
        return tokenObject.getExpire().after(new Date());
    }

    @Override
    public TokenAuthentication extractAuthentication(TokenObject tokenObject) {
        return new TokenAuthentication(
                tokenObject.getToken(),
                new User(tokenObject.getId(), tokenObject.getUsername(), tokenObject.getEmail()),
                tokenObject.getRoles()
        );
    }

    private List<Role> conversionToRole(List setCollection) {
        List<Role> list = new ArrayList<>();
        for (Object roleObj : setCollection) {
            list.add(Role.valueOf(String.valueOf(roleObj)));
        }
        return list;
    }
}
