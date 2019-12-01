package com.rined.deal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
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
