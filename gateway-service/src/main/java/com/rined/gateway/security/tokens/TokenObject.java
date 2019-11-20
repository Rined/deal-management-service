package com.rined.gateway.security.tokens;

import com.rined.gateway.security.model.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class TokenObject {
    private String token;
    private String tokenId;
    private String userId;
    private String username;
    private String email;
    private List<Role> roles;
    private Date expire;
}
