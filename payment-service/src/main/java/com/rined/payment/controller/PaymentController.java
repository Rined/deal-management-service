package com.rined.payment.controller;

import com.rined.payment.dto.request.IncomeRequest;
import com.rined.payment.dto.request.TransferRequest;
import com.rined.payment.dto.response.AccountDto;
import com.rined.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @GetMapping("/balance")
    public AccountDto getBalance(@RequestHeader("X-UserId") String userId) {
        return service.getBalance(userId);
    }

    @PostMapping("/income")
    public AccountDto income(@RequestHeader("X-UserId") String userId,
                             @RequestBody @Valid IncomeRequest request) {
        return service.income(userId, request);
    }

    @PostMapping("/transfer")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void moneyTransfer(@RequestBody @Valid TransferRequest request) {
        service.moneyTransfer(request);
    }

}
