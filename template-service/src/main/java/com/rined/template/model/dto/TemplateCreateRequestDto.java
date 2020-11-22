package com.rined.template.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TemplateCreateRequestDto extends TemplateRequestDto {

    @JsonProperty("id")
    @NotNull(message = "Template id is mandatory")
    @NotBlank(message = "Template id is mandatory")
    private String id;

}
