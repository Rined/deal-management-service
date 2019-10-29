package com.rined.client.model.collections;

import com.rined.client.model.TemplateData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    private String id;

    @Field("alias")
    private String alias;

    @DBRef
    @Field("templateRef")
    private Template template;

    @Field("value")
    private List<TemplateData> value;

    public Data(String alias, Template template, List<TemplateData> value) {
        id = ObjectId.get().toString();
        this.alias = alias;
        this.template = template;
        this.value = value;
    }
}
