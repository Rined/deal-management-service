package com.rined.payment.converter;

import com.rined.payment.dto.response.AccountDto;
import com.rined.payment.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class AccountConverterImpl implements AccountConverter {
    @Override
    public AccountDto convert(BankAccount account) {
        return new AccountDto(account.getBalance());
    }
}
