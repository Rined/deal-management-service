package com.rined.template.controllers.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserDto {
    private final String id;
    private final String username;
    private final String email;
}