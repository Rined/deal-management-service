package com.rined.orchestrator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rined.orchestrator.dto.SagaDealOut;
import com.rined.orchestrator.dto.SagaPaymentOut;
import com.rined.orchestrator.dto.SagaProposalOut;
import com.rined.orchestrator.dto.SagaResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SagaOutcomeSenderImpl implements SagaOutcomeSender {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${saga.outcome.topic}")
    private String sagaOutcome;

    @Value("${payment.income.topic}")
    private String paymentIncome;

    @Value("${proposal.income.topic}")
    private String proposalIncome;

    @Value("${deal.income.topic}")
    private String dealIncome;

    @Override
    public void sendSagaOutcome(SagaResult message) {
        kafkaTemplate.send(sagaOutcome, writeValueAsString(message));
    }

    @Override
    public void sendProposalIncome(SagaProposalOut message) {
        kafkaTemplate.send(proposalIncome, writeValueAsString(message));
    }

    @Override
    public void sendDealIncome(SagaDealOut message) {
        kafkaTemplate.send(dealIncome, writeValueAsString(message));
    }

    @Override
    public void sendPaymentIncome(SagaPaymentOut message) {
        kafkaTemplate.send(paymentIncome, writeValueAsString(message));
    }

    private <T> String writeValueAsString(T dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.warn("Convert to json problem", e);
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
