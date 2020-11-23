package com.rined.payment.service;

import com.rined.payment.converter.AccountConverter;
import com.rined.payment.dto.request.IncomeRequest;
import com.rined.payment.dto.request.TransferRequest;
import com.rined.payment.dto.response.AccountDto;
import com.rined.payment.dto.response.BankAccountDto;
import com.rined.payment.exception.NotFoundException;
import com.rined.payment.model.BankAccount;
import com.rined.payment.model.PaymentType;
import com.rined.payment.model.Transact;
import com.rined.payment.repository.AccountRepository;
import com.rined.payment.repository.TransactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final AccountRepository repository;
    private final AccountConverter converter;
    private final TransactRepository transactRepository;

    @Override
    public AccountDto getBalance(String userId) {
        return repository.findById(userId)
                .map(converter::convert)
                .orElseGet(() -> new AccountDto(BigDecimal.valueOf(0)));

    }

    @Override
    @Transactional
    public AccountDto income(String userId, IncomeRequest request) {
        BankAccount bankAccount = repository.findById(userId)
                .orElseGet(() -> new BankAccount(userId, BigDecimal.valueOf(0)));
        Transact transaction = new Transact(userId, userId, request.getAmount(), PaymentType.INCOME);
        transactRepository.save(transaction);
        bankAccount.addTransaction(transaction);
        bankAccount.increase(request.getAmount());
        return converter.convert(repository.save(bankAccount));
    }

    @Override
    @Transactional
    public void moneyTransfer(TransferRequest transferRequest) {
        BankAccount fromBankAccount = repository.findById(transferRequest.getFromUser())
                .orElseThrow(() -> new NotFoundException("Sender account not found!"));

        BankAccount toBankAccount = repository.findById(transferRequest.getToUser())
                .orElseGet(() -> new BankAccount(transferRequest.getToUser(), BigDecimal.valueOf(0)));

        log.info("Money transfer. Transfer request: {}", transferRequest);
        log.info("Money transfer. From account: {}", fromBankAccount);
        log.info("Money transfer. To account: {}", toBankAccount);

        BigDecimal bill = transferRequest.getAmount();
        Transact transaction = new Transact(transferRequest.getFromUser(), transferRequest.getToUser(), bill,
                PaymentType.MONEY_TRANSFER);
        transactRepository.save(transaction);

        fromBankAccount.addTransaction(transaction);
        toBankAccount.addTransaction(transaction);

        fromBankAccount.subtract(bill);
        toBankAccount.increase(bill);

        repository.saveAll(Arrays.asList(fromBankAccount, toBankAccount));
    }

    @Override
    @Transactional
    public List<BankAccountDto> findAll() {
        List<BankAccount> all = repository.findAll();
        for (BankAccount bankAccount : all) {
            log.trace("size {}", bankAccount.getTransactions().size());
        }
        return all.stream().map(acc -> new BankAccountDto(acc.getUserId(), acc.getBalance(), acc.getTransactions()))
                .collect(Collectors.toList());
    }

}
