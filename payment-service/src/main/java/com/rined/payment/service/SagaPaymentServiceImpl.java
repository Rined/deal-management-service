package com.rined.payment.service;

import com.rined.payment.dto.SagaPaymentRequest;
import com.rined.payment.dto.request.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SagaPaymentServiceImpl implements SagaPaymentService {
    private final PaymentService service;

    @Override
    public void sagaApprove(SagaPaymentRequest request) {
        TransferRequest transferRequest = new TransferRequest(request.getConsumerId(), request.getProviderId(),
                request.getAmount());
        service.moneyTransfer(transferRequest);
    }

}
