package com.rined.gateway.security.tokens;

import com.rined.gateway.security.model.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
@Builder
public class TokenObject {
    private String token;
    private String id;
    private String username;
    private Set<Role> roles;
    private Date expire;
}
