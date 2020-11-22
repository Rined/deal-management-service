package com.rined.payment.service;

import com.rined.payment.dto.request.IncomeRequest;
import com.rined.payment.dto.request.TransferRequest;
import com.rined.payment.dto.response.AccountDto;

public interface PaymentService {

    AccountDto getBalance(String userId);

    AccountDto income(String userId, IncomeRequest request);

    void moneyTransfer(TransferRequest transferRequest);
}
