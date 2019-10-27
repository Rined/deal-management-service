package com.rined.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilledTemplateData {

    @DBRef
    @Field("templateRef")
    private DocumentTemplate documentTemplate;

    @Field("value")
    private List<TemplateData> value;

}
