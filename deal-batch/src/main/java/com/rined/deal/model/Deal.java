package com.rined.deal.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("deal")
public class Deal {

    @Id
    private String id;

    @Field("providerId")
    private String providerId;

    @Field("consumerId")
    private String consumerId;

    @Field("info")
    private DealInfo dealInfo;

    @Setter
    @Field("state")
    private DealState state;

    public Deal(String providerId, String consumerId, DealInfo dealInfo, DealState state) {
        this.providerId = providerId;
        this.consumerId = consumerId;
        this.dealInfo = dealInfo;
        this.state = state;
    }
}
