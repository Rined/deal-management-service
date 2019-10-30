package com.rined.client.model;

import com.rined.client.model.collections.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateWide {

    @Id
    @Field("id")
    private String id;

    @DBRef
    @Field("ref")
    private Template template;

    public TemplateWide(Template template) {
        id = ObjectId.get().toHexString();
        this.template = template;
    }
}
