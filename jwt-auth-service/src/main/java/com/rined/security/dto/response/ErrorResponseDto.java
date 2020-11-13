package com.rined.security.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("description")
    private String description;

}
