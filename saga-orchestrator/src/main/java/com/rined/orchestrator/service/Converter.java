package com.rined.orchestrator.service;

import com.rined.orchestrator.dto.*;
import com.rined.orchestrator.model.DealSagaStatus;
import com.rined.orchestrator.model.Phase;
import com.rined.orchestrator.model.SagaOutbox;
import com.rined.orchestrator.model.Status;

public interface Converter {

    DealSagaStatus convertDealOutboxToNewDealSagaStatus(DealOutbox dealOutbox, Status status);

    SagaOutbox convertDealSagaStatusToSagaOutbox(DealSagaStatus dealSagaStatus, Phase phase);

    SagaProposalOut convertSagaOutboxToSagaProposalOutPositive(SagaOutbox sagaOutbox, SagaProposalRequestType type);

    SagaDealOut convertSagaOutboxToSagaDealOut(SagaOutbox sagaOutbox, SagaDealRequestType type);

    SagaPaymentOut convertSagaOutboxToSagaPaymentOut(SagaOutbox sagaOutbox);

    SagaResult convertSagaOutboxToSagaResult(SagaOutbox sagaOutbox, boolean result);
}
