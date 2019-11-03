package com.rined.template.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.template.model.FormatterTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormatterRequestDto {

    @NotNull
    @JsonProperty("name")
    @NotBlank(message = "Formatter name is mandatory")
    private String name;

    @NotNull
    @JsonProperty("value")
    @NotBlank(message = "Formatter value is mandatory")
    private String value;

    @NotNull
    @JsonProperty("type")
    private FormatterTypes type;
}
