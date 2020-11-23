package com.rined.payment.service;

import com.rined.payment.dto.SagaPaymentResult;

public interface SagaPaymentCooperationMember {

    void income(String message);

    void outcome(SagaPaymentResult sagaDealResult);

}
