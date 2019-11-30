package com.rined.deal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String dealProviderId;

    @Field("consumerId")
    private String dealConsumerId;

    @Field("info")
    private DealInfo dealInfo;

    @Field("state")
    private DealState state;

    public Deal(String dealProviderId, String dealConsumerId, DealInfo dealInfo, DealState state) {
        this.dealProviderId = dealProviderId;
        this.dealConsumerId = dealConsumerId;
        this.dealInfo = dealInfo;
        this.state = state;
    }
}
