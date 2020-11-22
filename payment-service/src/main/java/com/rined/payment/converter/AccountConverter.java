package com.rined.payment.converter;

import com.rined.payment.dto.response.AccountDto;
import com.rined.payment.model.BankAccount;

public interface AccountConverter {

    AccountDto convert(BankAccount account);

}
