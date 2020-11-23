package com.rined.orchestrator.service;

import com.rined.orchestrator.dto.*;
import com.rined.orchestrator.model.SagaOutbox;
import com.rined.orchestrator.repositories.SagaOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.rined.orchestrator.model.Phase.*;

@Service
@RequiredArgsConstructor
public class OutboxSenderImpl implements OutboxSender {
    private final SagaOutcomeSender sender;
    private final SagaOutboxRepository repository;
    private final Converter converter;

    @Override
    @Scheduled(fixedDelay = 1000)
    public void sendOutbox() {
        List<SagaOutbox> allByPhaseIn =
                repository.findAllByPhaseIn(Arrays.asList(
                        PHASE_1_PROPOSAL, PHASE_1_PROPOSAL_REJECT,
                        PHASE_2_DEAL, PHASE_2_DEAL_REJECT,
                        PHASE_3_PAYMENT,
                        PHASE_4_RETURN,
                        FULLY_REJECTED
                ));
        for (SagaOutbox sagaOutbox : allByPhaseIn) {
            switch (sagaOutbox.getPhase()) {
                case PHASE_1_PROPOSAL:
                    SagaProposalOut out = converter.convertSagaOutboxToSagaProposalOutPositive(sagaOutbox, SagaProposalRequestType.APPROVE);
                    sender.sendProposalIncome(out);
                    sagaOutbox.setPhase(PHASE_1_PROPOSAL_AWAIT);
                    break;

                case PHASE_1_PROPOSAL_REJECT:
                    SagaProposalOut sagaProposalOutRej = converter.convertSagaOutboxToSagaProposalOutPositive(sagaOutbox, SagaProposalRequestType.REJECT);
                    sender.sendProposalIncome(sagaProposalOutRej);
                    sagaOutbox.setPhase(PHASE_1_PROPOSAL_AWAIT);
                    break;

                case PHASE_2_DEAL:
                    SagaDealOut dealOut = converter.convertSagaOutboxToSagaDealOut(sagaOutbox, SagaDealRequestType.APPROVE);
                    sender.sendDealIncome(dealOut);
                    sagaOutbox.setPhase(PHASE_2_DEAL_AWAIT);
                    break;

                case PHASE_2_DEAL_REJECT:
                    SagaDealOut dealOutReject = converter.convertSagaOutboxToSagaDealOut(sagaOutbox, SagaDealRequestType.REJECT);
                    sender.sendDealIncome(dealOutReject);
                    sagaOutbox.setPhase(PHASE_2_DEAL_AWAIT);
                    break;

                case PHASE_3_PAYMENT:
                    SagaPaymentOut paymentOut = converter.convertSagaOutboxToSagaPaymentOut(sagaOutbox);
                    sender.sendPaymentIncome(paymentOut);
                    sagaOutbox.setPhase(PHASE_3_PAYMENT_AWAIT);
                    break;

                case PHASE_4_RETURN:
                    SagaResult result = converter.convertSagaOutboxToSagaResult(sagaOutbox, true);
                    sender.sendSagaOutcome(result);
                    sagaOutbox.setPhase(DONE);
                    break;

                case FULLY_REJECTED:
                    SagaResult resultReject = converter.convertSagaOutboxToSagaResult(sagaOutbox, false);
                    sender.sendSagaOutcome(resultReject);
                    sagaOutbox.setPhase(DIED);
                    break;
            }
            repository.save(sagaOutbox);
        }
    }
}
