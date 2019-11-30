package com.rined.deal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

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

}
