package com.rined.security.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto {

    @JsonProperty("user")
    @NotNull(message = "Username is mandatory")
    @NotEmpty(message = "Username is mandatory")
    private String login;

    @JsonProperty("password")
    @NotNull(message = "Password is mandatory")
    @NotEmpty(message = "Password is mandatory")
    private String password;

    @Email
    @JsonProperty("email")
    @NotNull(message = "Email is mandatory")
    @NotEmpty(message = "Email is mandatory")
    private String email;

    @JsonProperty("roles")
    @NotNull(message = "Roles is mandatory")
    @NotEmpty(message = "Roles is mandatory")
    private Set<Role> roles;

}

