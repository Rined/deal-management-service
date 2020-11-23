package com.rined.deal.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rined.deal.exception.InvalidStateChange;
import com.rined.deal.exception.NotFoundException;
import com.rined.deal.model.Deal;
import com.rined.deal.model.DealState;
import com.rined.deal.model.dto.SagaResult;
import com.rined.deal.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@EnableKafka
@RequiredArgsConstructor
public class SagaCompleteServiceImpl implements SagaCompleteService {
    private final ObjectMapper objectMapper;
    private final DealRepository repository;
    private final StateChangeChecker checker;

    @Override
    @KafkaListener(topics = "saga_outcome")
    public void sagaComplete(String message) {
        try {
            log.info("saga_outcome message: {}", message);
            SagaResult sagaResult = objectMapper.readValue(message, SagaResult.class);
            String dealId = sagaResult.getDealId();
            Optional<Deal> dealOptional = repository.findById(dealId);
            Deal deal = dealOptional.orElseThrow(() -> new NotFoundException("Deal not found!"));
            DealState newState = checkState(deal, sagaResult.isResult() ? DealState.IN_WORK : DealState.PROCESS_ERROR);
            deal.setState(newState);
            repository.save(deal);
        } catch (Exception e) {
            log.error("Saga result error", e);
        }

    }

    private DealState checkState(Deal deal, DealState newState) {
        if (checker.isInvalidStateChange(deal.getState(), newState)) {
            throw new InvalidStateChange("Invalid state change");
        }
        return newState;
    }
}
