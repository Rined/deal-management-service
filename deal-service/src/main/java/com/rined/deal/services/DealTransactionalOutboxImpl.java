package com.rined.deal.services;

import com.rined.deal.exception.AlreadyExistsException;
import com.rined.deal.exception.InvalidStateChange;
import com.rined.deal.exception.NotFoundException;
import com.rined.deal.model.Deal;
import com.rined.deal.model.DealState;
import com.rined.deal.model.DealOutbox;
import com.rined.deal.model.OutboxStatus;
import com.rined.deal.repositories.DealRepository;
import com.rined.deal.repositories.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DealTransactionalOutboxImpl implements DealTransactionalOutbox {
    private final DealRepository dealRepository;
    private final OutboxRepository outboxRepository;
    private final StateChangeChecker checker;

    @Override
    @Transactional
    public Deal persistDeal(String dealId, String consumerId, DealState newState) {
        if (outboxRepository.existsById(dealId)) {
            throw new AlreadyExistsException("Deal in process!");
        }

        Deal deal = dealRepository.findByIdAndConsumerId(dealId, consumerId)
                .orElseThrow(() -> new NotFoundException("Deal with id % not found", dealId));

        if (checker.isInvalidStateChange(deal.getState(), newState))
            throw new InvalidStateChange("Invalid state change");

        deal.setState(newState);
        Deal savedDeal = dealRepository.save(deal);

        DealOutbox dealOutbox = new DealOutbox(deal.getId(),deal.getDealInfo().getProposalId(),
                 deal.getProviderId(), deal.getConsumerId(), deal.getPrice(), OutboxStatus.NEW);
        outboxRepository.save(dealOutbox);

        return savedDeal;
    }
}
