package com.rined.orchestrator.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DealOutbox {

    private String id;  // dealId

    private String proposalId;

    private String providerId;

    private String consumerId;

    private BigDecimal price;

}
