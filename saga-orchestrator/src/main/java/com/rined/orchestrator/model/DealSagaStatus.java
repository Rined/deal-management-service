package com.rined.orchestrator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "deal_saga")
@Entity(name = "deal_saga")
public class DealSagaStatus {

    @Id
    private String id;  // dealId

    @Column(name = "proposalId")
    private String proposalId;

    @Column(name = "providerId")
    private String providerId;

    @Column(name = "consumerId")
    private String consumerId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
