package com.rined.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
//
//    @JsonProperty("user")
//    private String name;
//
//    @JsonProperty("roles")
//    private Set<Role> roles;

    @NotNull
    @NotEmpty
    @JsonProperty("token")
    private String token;
}
