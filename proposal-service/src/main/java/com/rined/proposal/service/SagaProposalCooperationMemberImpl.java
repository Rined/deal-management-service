package com.rined.proposal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rined.proposal.model.dto.SagaProposalRequest;
import com.rined.proposal.model.dto.SagaProposalResult;
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
public class SagaProposalCooperationMemberImpl implements SagaProposalCooperationMember {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;
    private final SagaProposalService sagaProposalService;

    @Value("${proposal.outcome.topic}")
    private String proposalOutcome;

    @Override
    @KafkaListener(topics = "proposal_income")
    public void income(String message) {
        SagaProposalRequest request = null;
        try {
            request = mapper.readValue(message, SagaProposalRequest.class);
            switch (request.getType()) {
                case APPROVE:
                    sagaProposalService.sagaApprove(request);
                    break;
                case REJECT:
                    sagaProposalService.sagaReject(request);
                    break;
            }
            outcome(SagaProposalResult.of(request, true));
        } catch (Exception e) {
            log.error("Json parse exception", e);
            Objects.requireNonNull(request, "Json parse exception");
            outcome(SagaProposalResult.of(request, false));
        }
    }

    @Override
    public void outcome(SagaProposalResult sagaProposalResult) {
        try {
            String result = mapper.writeValueAsString(sagaProposalResult);
            kafkaTemplate.send(proposalOutcome, result);
        } catch (Exception e) {
            log.warn("Send message problem");
        }
    }
}
