package com.rined.client.model;

import com.rined.client.model.collections.Data;
import com.rined.client.model.collections.Template;
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
    List<TemplateWide> active;

    @Field("sent")
    List<Data> sent;

    @Field("completed")
    List<Data> completed;

}
