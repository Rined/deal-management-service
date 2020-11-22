package com.rined.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Account")
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @Column(name = "id")
    private String userId;

    @Column(name = "balance")
    @DecimalMin(value = "0")
    private BigDecimal balance;

    @OrderColumn(name = "transaction_order")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "accs_trans")
    private List<Transact> transactions = new ArrayList<>();

    public BankAccount(String userId, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public void increase(BigDecimal increase) {
        balance = balance.add(increase);
    }

    public void subtract(BigDecimal subtract) {
        balance = balance.subtract(subtract);
    }

    public void addTransaction(Transact transaction){
        transactions.add(transaction);
    }
}
