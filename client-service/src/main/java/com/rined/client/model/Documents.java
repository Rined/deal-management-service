package com.rined.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Documents {

    @DBRef
    @Field("active")
    List<DocumentTemplate> active;

    @Field("sent")
    List<FilledTemplateData> sent;

    @Field("completed")
    List<FilledTemplateData> completed;

}
