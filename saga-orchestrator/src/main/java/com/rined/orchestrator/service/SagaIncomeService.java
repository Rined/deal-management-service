package com.rined.orchestrator.service;

public interface SagaIncomeService {

    void readSagaIncome(String message);

    void readProposalOutcome(String message);

    void readDealOutcome(String message);

    void readPaymentOutcome(String message);

}
