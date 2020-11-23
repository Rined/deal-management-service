package com.rined.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SagaPaymentResult {

    private String dealId;

    private String consumerId;

    private String providerId;

    private BigDecimal amount;

    private boolean result;

    public static SagaPaymentResult of(SagaPaymentRequest request, boolean result) {
        return new SagaPaymentResult(request.getDealId(), request.getConsumerId(), request.getProviderId(), request.getAmount(), result);
    }
}
