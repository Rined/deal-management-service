package com.rined.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rined.payment.dto.SagaPaymentRequest;
import com.rined.payment.dto.SagaPaymentResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Slf4j
@Service
@EnableKafka
@RequiredArgsConstructor
public class SagaPaymentCooperationMemberImpl implements SagaPaymentCooperationMember {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;
    private final SagaPaymentService sagaPaymentService;

    @Value("${payment.outcome.topic}")
    private String paymentOutcome;

    @Override
    @KafkaListener(topics = "payment_income")
    public void income(String message) {
        SagaPaymentRequest request = null;
        try {
            request = mapper.readValue(message, SagaPaymentRequest.class);
            sagaPaymentService.sagaApprove(request);
            outcome(SagaPaymentResult.of(request, true));
        } catch (Exception e) {
            log.error("Process error", e);
            Objects.requireNonNull(request, "Json parse exception");
            outcome(SagaPaymentResult.of(request, false));
        }
    }

    @Override
    public void outcome(SagaPaymentResult sagaPaymentResult) {
        try {
            String result = mapper.writeValueAsString(sagaPaymentResult);
            kafkaTemplate.send(paymentOutcome, result);
        } catch (Exception e) {
            log.warn("Send message problem", e);
        }
    }
}
