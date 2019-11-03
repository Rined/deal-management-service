package com.rined.template.model;

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
    private String templateName;

    @Field("fields")
    private List<TemplateField> fields;

    @Field("formatters")
    private List<Formatter> formatters;

    public Template(String templateName, List<TemplateField> fields, List<Formatter> formatters) {
        this.templateName = templateName;
        this.fields = fields;
        this.formatters = formatters;
    }
}
