package com.rined.template.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateField {

    @Field("name")
    @JsonProperty("name")
    private String fieldName;

    @Field("description")
    @JsonProperty("description")
    private String description;
}
