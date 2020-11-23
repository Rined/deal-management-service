package com.rined.orchestrator.service;

import com.rined.orchestrator.dto.*;
import com.rined.orchestrator.model.DealSagaStatus;
import com.rined.orchestrator.model.Phase;
import com.rined.orchestrator.model.SagaOutbox;
import com.rined.orchestrator.model.Status;
import org.springframework.stereotype.Component;

@Component
public class ConverterImpl implements Converter {

    @Override
    public DealSagaStatus convertDealOutboxToNewDealSagaStatus(DealOutbox dealOutbox, Status status) {
        return new DealSagaStatus(dealOutbox.getId(), dealOutbox.getProposalId(), dealOutbox.getProviderId(),
                dealOutbox.getConsumerId(), dealOutbox.getPrice(), status);
    }

    @Override
    public SagaOutbox convertDealSagaStatusToSagaOutbox(DealSagaStatus dealSagaStatus, Phase phase) {
        return new SagaOutbox(dealSagaStatus.getId(), dealSagaStatus.getProposalId(), dealSagaStatus.getProviderId(), dealSagaStatus.getConsumerId(), dealSagaStatus.getPrice(), phase);
    }

    @Override
    public SagaProposalOut convertSagaOutboxToSagaProposalOutPositive(SagaOutbox sagaOutbox, SagaProposalRequestType type) {
        return new SagaProposalOut(
                sagaOutbox.getId(), sagaOutbox.getProposalId(), type
        );
    }

    @Override
    public SagaDealOut convertSagaOutboxToSagaDealOut(SagaOutbox sagaOutbox, SagaDealRequestType type) {
        return new SagaDealOut(
                sagaOutbox.getId(), sagaOutbox.getProposalId(), type
        );
    }

    @Override
    public SagaPaymentOut convertSagaOutboxToSagaPaymentOut(SagaOutbox sagaOutbox) {
        return new SagaPaymentOut(
                sagaOutbox.getId(), sagaOutbox.getConsumerId(), sagaOutbox.getProviderId(), sagaOutbox.getPrice()
        );
    }

    @Override
    public SagaResult convertSagaOutboxToSagaResult(SagaOutbox sagaOutbox, boolean result) {
        return new SagaResult(sagaOutbox.getId(), result);
    }

}
