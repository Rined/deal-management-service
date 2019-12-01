package com.rined.deal.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DealInfo {

    @Field("proposalId")
    private String proposalId;

    @Field("dealTitle")
    private String dealTitle;

    @Field("subject")
    private String dealSubject;

    @Setter
    @Field("fields")
    private List<FieldInfo> info;

}
