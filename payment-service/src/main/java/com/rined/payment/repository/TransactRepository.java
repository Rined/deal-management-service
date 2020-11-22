package com.rined.payment.repository;

import com.rined.payment.model.Transact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactRepository extends JpaRepository<Transact, String> {
}
