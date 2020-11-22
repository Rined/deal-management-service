package com.rined.crud.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class AuthenticatedUser {
    private final String userId;
    private final String username;
    private final String email;
}
