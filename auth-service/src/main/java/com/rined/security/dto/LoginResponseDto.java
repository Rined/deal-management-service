package com.rined.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    @JsonProperty("user")
    private String name;

    @JsonProperty("roles")
    private Set<Role> roles;

    @JsonProperty("token")
    private String token;
}
