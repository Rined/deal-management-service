package com.rined.orchestrator.service;

import com.rined.orchestrator.model.DealSagaStatus;
import com.rined.orchestrator.model.Phase;
import com.rined.orchestrator.model.SagaOutbox;
import com.rined.orchestrator.repositories.DealSagaStatusRepository;
import com.rined.orchestrator.repositories.SagaOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OutboxServiceImpl implements OutboxService {
    private final DealSagaStatusRepository repository;
    private final SagaOutboxRepository outboxRepository;
    private final Converter converter;

    @Override
    @Transactional
    public void saveToOutbox(DealSagaStatus dealSagaStatus, Phase phase) {
        repository.save(dealSagaStatus);
        SagaOutbox outbox = converter.convertDealSagaStatusToSagaOutbox(dealSagaStatus, phase);
        outboxRepository.save(outbox);
    }
}
