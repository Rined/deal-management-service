package com.rined.gateway.security.tokens;

import com.rined.gateway.properties.JWTProperties;
import com.rined.gateway.security.model.Role;
import com.rined.gateway.security.model.TokenAuthentication;
import com.rined.gateway.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JWTCheckTokenService implements TokenService {

    private final JWTProperties jwtProperties;

    @Override
    public TokenObject transform(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return new TokenObject.TokenObjectBuilder()
                .token(token)
                .id(body.getId())
                .expire(body.getExpiration())
                .username(body.getSubject())
                .roles(conversionToRole(body.get("roles", Set.class)))
                .build();
    }

    @Override
    public boolean validate(TokenObject tokenObject) {
        return tokenObject.getExpire().before(new Date());
    }

    @Override
    public TokenAuthentication extractAuthentication(TokenObject tokenObject) {
        return new TokenAuthentication(
                tokenObject.getToken(),
                new User(tokenObject.getId(), tokenObject.getUsername()),
                tokenObject.getRoles()
        );
    }

    private Set<Role> conversionToRole(Set setCollection) {
        Set<Role> set = new HashSet<>();
        for (Object roleObj : setCollection) {
            set.add((Role) roleObj);
        }
        return set;
    }
}
