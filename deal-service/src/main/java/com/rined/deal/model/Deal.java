package com.rined.deal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
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
