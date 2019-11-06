package com.rined.template.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document("template")
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    @Id
    private String id;

    @Field("name")
    @JsonProperty("name")
    private String templateName;

    @Field("fields")
    @JsonProperty("fields")
    private List<TemplateField> fields;

    @Field("format")
    @JsonProperty("format")
    private String format;

    public Template(String templateName, List<TemplateField> fields, String format) {
        this.templateName = templateName;
        this.fields = fields;
        this.format = format;
    }
}
