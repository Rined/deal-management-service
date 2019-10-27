package com.rined.client.model;

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
public class DocumentTemplate {

    @Id
    private String id;

    @Field("name")
    private String templateName;

    @Field("fields")
    private List<TemplateField> fields;

    public DocumentTemplate(String templateName, List<TemplateField> fields) {
        this.templateName = templateName;
        this.fields = fields;
    }
}
