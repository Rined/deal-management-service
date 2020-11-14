package com.rined.proposal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDto {

    @JsonProperty("reason")
    private final String reason;

    @JsonProperty("description")
    private final String description;

}
