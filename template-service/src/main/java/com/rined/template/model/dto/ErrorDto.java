package com.rined.template.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("description")
    private String description;

}
