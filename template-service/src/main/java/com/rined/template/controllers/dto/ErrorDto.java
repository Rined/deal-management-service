package com.rined.template.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("description")
    private String description;

}
