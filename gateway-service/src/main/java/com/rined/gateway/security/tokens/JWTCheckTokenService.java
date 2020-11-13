package com.rined.gateway.security.tokens;

import com.rined.gateway.properties.JWTProperties;
import com.rined.gateway.security.model.Role;
import com.rined.gateway.security.model.TokenAuthentication;
import com.rined.gateway.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                    .tokenId(body.getId())
                    .username(body.getSubject())
                    .expire(body.getExpiration())
                    .userId(body.get("userId", String.class))
                    .email(body.get("email", String.class))
                    .role(Role.valueOf(body.get("role", String.class)))
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
                new User(tokenObject.getUserId(),
                        tokenObject.getUsername(),
                        tokenObject.getEmail()),
                tokenObject.getRole()
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
