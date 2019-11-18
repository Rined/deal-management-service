package com.rined.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    @NotNull
    @NotEmpty
    @JsonProperty("user")
    private String login;

    @NotNull
    @NotEmpty
    @JsonProperty("password")
    private String password;

    @Email
    @JsonProperty("email")
    private String email;

}

