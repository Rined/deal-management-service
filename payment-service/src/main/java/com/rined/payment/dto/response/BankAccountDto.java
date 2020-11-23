package com.rined.payment.dto.response;

import com.rined.payment.model.Transact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {
    private String userId;

    private BigDecimal balance;

    private List<Transact> transactions = new ArrayList<>();
}
