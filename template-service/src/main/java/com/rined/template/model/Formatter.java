package com.rined.template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("formatter")
public class Formatter {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("value")
    private String value;

    @Field("type")
    private FormatterTypes type;

    public Formatter(String name, String value, FormatterTypes type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }
}
