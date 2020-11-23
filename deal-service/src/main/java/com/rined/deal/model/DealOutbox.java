package com.rined.deal.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Document("outbox")
public class DealOutbox {

    @Id
    private String id;  // dealId

    @Field("proposalId")
    private String proposalId;

    @Field("providerId")
    private String providerId;

    @Field("consumerId")
    private String consumerId;

    @Field("price")
    private BigDecimal price;

    @Setter
    @JsonIgnore
    @Field("status")
    private OutboxStatus outboxStatus;

    public DealOutbox(String dealId,
                      String proposalId,
                      String providerId,
                      String consumerId,
                      BigDecimal price,
                      OutboxStatus state) {
        this.id = dealId;
        this.proposalId = proposalId;
        this.providerId = providerId;
        this.consumerId = consumerId;
        this.price = price;
        this.outboxStatus = state;
    }
}
