package com.rined.deal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldInfo {

    @Field("uuid")
    private String uuid;

    @Field("description")
    private String description;

    @Field("value")
    private String value;

    public FieldInfo(String uuid, String description) {
        this.uuid = uuid;
        this.description = description;
    }
}
