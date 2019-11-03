package com.rined.template.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateField {

    @Field("name")
    private String fieldName;

    @Field("description")
    private String description;
}
