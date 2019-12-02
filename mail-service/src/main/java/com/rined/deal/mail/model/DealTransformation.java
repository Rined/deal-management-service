package com.rined.deal.mail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("transformation")
public class DealTransformation {
    @Id
    private String id;

    @Field("action")
    private String action;

    @Field("title")
    private String title;

    @Field("template")
    private String template;

    public DealTransformation(String action, String title, String template) {
        this.action = action;
        this.title = title;
        this.template = template;
    }
}
