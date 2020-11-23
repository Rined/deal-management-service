package com.rined.deal.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rined.deal.model.dto.SagaDealRequest;
import com.rined.deal.model.dto.SagaDealResult;
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
public class SagaDealCooperationMemberImpl implements SagaDealCooperationMember {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;
    private final SagaDealService sagaDealService;

    @Value("${deal.outcome.topic}")
    private String dealOutcome;

    @Override
    @KafkaListener(topics = "deal_income")
    public void income(String message) {
        SagaDealRequest request = null;
        try {
            request = mapper.readValue(message, SagaDealRequest.class);
            switch (request.getType()) {
                case APPROVE:
                    sagaDealService.sagaApprove(request);
                    break;
                case REJECT:
                    sagaDealService.sagaReject(request);
                    break;
            }
            outcome(SagaDealResult.of(request, true));
        } catch (Exception e) {
            log.error("Json parse exception", e);
            Objects.requireNonNull(request, "Json parse exception");
            outcome(SagaDealResult.of(request, false));
        }
    }

    @Override
    public void outcome(SagaDealResult sagaDealResult) {
        try {
            String result = mapper.writeValueAsString(sagaDealResult);
            kafkaTemplate.send(dealOutcome, result);
        } catch (Exception e) {
            log.warn("Send message problem");
        }
    }
}
