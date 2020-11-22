package com.rined.payment.repository;

import com.rined.payment.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount, String> {

}
