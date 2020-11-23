package com.rined.orchestrator.service;

import com.rined.orchestrator.dto.SagaDealOut;
import com.rined.orchestrator.dto.SagaPaymentOut;
import com.rined.orchestrator.dto.SagaProposalOut;
import com.rined.orchestrator.dto.SagaResult;

public interface SagaOutcomeSender {

    void sendSagaOutcome(SagaResult message);

    void sendProposalIncome(SagaProposalOut message);

    void sendDealIncome(SagaDealOut message);

    void sendPaymentIncome(SagaPaymentOut message);

}
