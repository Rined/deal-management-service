package com.rined.template.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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
