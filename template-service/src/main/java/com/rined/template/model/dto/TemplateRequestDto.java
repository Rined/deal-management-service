package com.rined.template.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.template.model.TemplateField;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class TemplateRequestDto {

    @JsonProperty("name")
    @NotNull(message = "Template name is mandatory")
    @NotBlank(message = "Template name is mandatory")
    private String templateName;

    @JsonProperty("fields")
    private List<TemplateField> fields;

    @JsonProperty("format")
    @NotNull(message = "Template text is mandatory")
    @NotBlank(message = "Template text is mandatory")
    private String format;

}
