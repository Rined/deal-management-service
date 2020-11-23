package com.rined.orchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SagaPaymentIn {

    private String dealId;

    private String consumerId;

    private String providerId;

    private BigDecimal amount;

    private boolean result;

}
