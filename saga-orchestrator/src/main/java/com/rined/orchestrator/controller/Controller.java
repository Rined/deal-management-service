package com.rined.orchestrator.controller;

import com.rined.orchestrator.model.DealSagaStatus;
import com.rined.orchestrator.model.SagaOutbox;
import com.rined.orchestrator.repositories.DealSagaStatusRepository;
import com.rined.orchestrator.repositories.SagaOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final DealSagaStatusRepository statusRepository;
    private final SagaOutboxRepository outboxRepository;

    @GetMapping("/status")
    public List<DealSagaStatus> allStatus() {
        return statusRepository.findAll();
    }

    @GetMapping("/outbox")
    public List<SagaOutbox> allOutbox() {
        return outboxRepository.findAll();
    }
}
