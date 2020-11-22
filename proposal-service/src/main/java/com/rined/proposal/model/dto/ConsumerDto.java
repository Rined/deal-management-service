package com.rined.proposal.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ConsumerDto {
    private final String id;
    private final String username;
    private final String email;
}