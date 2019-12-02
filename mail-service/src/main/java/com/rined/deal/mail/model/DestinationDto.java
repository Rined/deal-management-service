package com.rined.deal.mail.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class DestinationDto {
    @JsonProperty("user")
    private final String userName;

    @JsonProperty("action")
    private final String action;

    @JsonProperty("address")
    private final String emailAddress;
}
