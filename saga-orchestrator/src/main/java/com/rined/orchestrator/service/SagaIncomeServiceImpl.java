package com.rined.orchestrator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rined.orchestrator.dto.DealOutbox;
import com.rined.orchestrator.dto.SagaDealIn;
import com.rined.orchestrator.dto.SagaPaymentIn;
import com.rined.orchestrator.dto.SagaProposalIn;
import com.rined.orchestrator.model.DealSagaStatus;
import com.rined.orchestrator.model.Phase;
import com.rined.orchestrator.model.Status;
import com.rined.orchestrator.repositories.DealSagaStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.rined.orchestrator.model.Phase.*;
import static com.rined.orchestrator.model.Status.*;

@Slf4j
@Service
@EnableKafka
@RequiredArgsConstructor
public class SagaIncomeServiceImpl implements SagaIncomeService {
    private final ObjectMapper objectMapper;
    private final Converter converter;
    private final OutboxService outboxService;
    private final DealSagaStatusRepository repository;

    @Override
    @KafkaListener(topics = "saga_income")
    public void readSagaIncome(String message) {
        try {
            DealOutbox dealOutbox = objectMapper.readValue(message, DealOutbox.class);
            DealSagaStatus dealSagaStatus = converter.convertDealOutboxToNewDealSagaStatus(dealOutbox, Status.PENDING);
            outboxService.saveToOutbox(dealSagaStatus, Phase.PHASE_1_PROPOSAL);
        } catch (Exception e) {
            log.warn("readSagaIncome error", e);
        }

    }

    @Override
    @KafkaListener(topics = "proposal_outcome")
    public void readProposalOutcome(String message) {
        try {
            SagaProposalIn sagaProposalIn = objectMapper.readValue(message, SagaProposalIn.class);
            DealSagaStatus saga = getSaga(repository.findById(sagaProposalIn.getDealId()));
            switch (sagaProposalIn.getType()) {
                case REJECT:
                    saga.setStatus(REJECTED);
                    Phase phase = sagaProposalIn.isResult() ? FULLY_REJECTED : PHASE_1_PROPOSAL_REJECT;
                    outboxService.saveToOutbox(saga, phase);
                    break;
                case APPROVE:
                    boolean result = sagaProposalIn.isResult();
                    saga.setStatus(result ? PROPOSAL_COMPLETE : REJECTED);
                    Phase approvePhase = result ? PHASE_2_DEAL : FULLY_REJECTED;
                    outboxService.saveToOutbox(saga, approvePhase);
                    break;
            }
        } catch (Exception e) {
            log.warn("readProposalOutcome error", e);
        }
    }

    @Override
    @KafkaListener(topics = "deal_outcome")
    public void readDealOutcome(String message) {
        try {
            SagaDealIn sagaDealIn = objectMapper.readValue(message, SagaDealIn.class);
            DealSagaStatus saga = getSaga(repository.findById(sagaDealIn.getDealId()));
            switch (sagaDealIn.getType()) {
                case REJECT:
                    saga.setStatus(REJECTED);
                    Phase phase = sagaDealIn.isResult() ? PHASE_1_PROPOSAL_REJECT : PHASE_2_DEAL_REJECT;
                    outboxService.saveToOutbox(saga, phase);
                    break;
                case APPROVE:
                    boolean result = sagaDealIn.isResult();
                    saga.setStatus(result ? DEAL_COMPLETE : REJECTED);
                    Phase approvePhase = result ? PHASE_3_PAYMENT : PHASE_1_PROPOSAL_REJECT;
                    outboxService.saveToOutbox(saga, approvePhase);
                    break;
            }
        } catch (Exception e) {
            log.warn("readDealOutcome error", e);
        }
    }

    @Override
    @KafkaListener(topics = "payment_outcome")
    public void readPaymentOutcome(String message) {
        try {
            SagaPaymentIn sagaPaymentIn = objectMapper.readValue(message, SagaPaymentIn.class);
            DealSagaStatus saga = getSaga(repository.findById(sagaPaymentIn.getDealId()));
            boolean result = sagaPaymentIn.isResult();
            saga.setStatus(result ? PAYMENT_COMPLETE : REJECTED);
            Phase phase = result ? PHASE_4_RETURN : PHASE_2_DEAL_REJECT;
            outboxService.saveToOutbox(saga, phase);
        } catch (Exception e) {
            log.warn("readPaymentOutcome error", e);
        }
    }

    private DealSagaStatus getSaga(Optional<DealSagaStatus> optional) {
        return optional.orElseThrow(() -> new IllegalArgumentException("Saga status not found"));
    }
}
