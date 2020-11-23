package com.rined.payment.service;

import com.rined.payment.dto.SagaPaymentRequest;

public interface SagaPaymentService {

    void sagaApprove(SagaPaymentRequest request);

}
