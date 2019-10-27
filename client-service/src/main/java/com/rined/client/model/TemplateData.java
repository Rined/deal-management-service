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
public class TemplateData {

    @Field("name")
    private String templateFieldName;

    @Field("description")
    private String description;
}
