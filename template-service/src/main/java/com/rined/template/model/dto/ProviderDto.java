package com.rined.template.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ProviderDto {
    private final String id;
    private final String username;
    private final String email;
}