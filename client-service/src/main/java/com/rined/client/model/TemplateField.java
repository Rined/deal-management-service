package com.rined.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TemplateField {

    @Field("name")
    private String fieldName;

    @Field("description")
    private String description;
}
