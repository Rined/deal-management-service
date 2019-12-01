package com.rined.deal.services;

import com.rined.deal.exception.InvalidStateChange;
import com.rined.deal.exception.NotFoundException;
import com.rined.deal.model.Deal;
import com.rined.deal.model.DealState;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.ProviderDto;
import com.rined.deal.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.rined.deal.model.DealState.*;

@Service
@RequiredArgsConstructor
public class DealStateServiceImpl implements DealStateService {
    private final DealRepository repository;

    @Override
    public Deal declineProvider(String dealId, ProviderDto provider) {
        Deal deal = repository.findByIdAndProviderId(dealId, provider.getId())
                .orElseThrow(() -> new NotFoundException("Deal with id % not found", dealId));
        if (isInvalidStateChange(deal.getState(), PROVIDER_DECLINE)) {
            throw new InvalidStateChange("Invalid state change");
        }
        deal.setState(PROVIDER_DECLINE);
        return repository.save(deal);
    }

    @Override
    public Deal declineConsumer(String dealId, ConsumerDto consumer) {
        Deal deal = repository.findByIdAndConsumerId(dealId, consumer.getId())
                .orElseThrow(() -> new NotFoundException("Deal with id % not found", dealId));
        if (isInvalidStateChange(deal.getState(), CONSUMER_DECLINE)) {
            throw new InvalidStateChange("Invalid state change");
        }
        deal.setState(CONSUMER_DECLINE);
        return repository.save(deal);
    }


    private boolean isInvalidStateChange(DealState currentState, DealState newState) {
        switch (newState) {
            case CONSUMER_DECLINE:
            case PROVIDER_DECLINE:
                return currentState == DONE
                        || currentState == CONSUMER_DECLINE
                        || currentState == PROVIDER_DECLINE;
            default:
                return newState.getNextState() != currentState;
        }
    }
}
