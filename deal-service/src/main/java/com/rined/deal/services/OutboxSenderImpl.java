package com.rined.deal.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rined.deal.model.DealOutbox;
import com.rined.deal.model.OutboxStatus;
import com.rined.deal.repositories.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxSenderImpl implements OutboxSender {
    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    @Value("${saga.income.topic}")
    private String sageIncome;

    @Override
    @Scheduled(fixedDelay = 1000)
    public void send() {
        List<DealOutbox> allByStatus = outboxRepository.findAllByOutboxStatus(OutboxStatus.NEW);
        for (DealOutbox outbox : allByStatus) {
            try {
                kafkaTemplate.send(sageIncome, mapper.writeValueAsString(outbox));
                outbox.setOutboxStatus(OutboxStatus.SENT);
                outboxRepository.save(outbox);
            } catch (JsonProcessingException e) {
                log.warn("Send message problem", e);
            }
        }
    }
}
