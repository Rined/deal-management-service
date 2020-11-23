package com.rined.deal.services;

import com.rined.deal.model.DealState;
import org.springframework.stereotype.Component;

import static com.rined.deal.model.DealState.*;

@Component
public class StateChangeCheckerImpl implements StateChangeChecker {

    @Override
    public boolean isInvalidStateChange(DealState currentState, DealState newState) {
        switch (newState) {
            case PROCESS_ERROR:
                return currentState != PAYMENT_VERIFICATION;
            case CONSUMER_DECLINE:
            case PROVIDER_DECLINE:
                return currentState == DONE
                        || currentState == CONSUMER_DECLINE
                        || currentState == IN_WORK
                        || currentState == PROVIDER_DECLINE;
            default:
                return currentState.getNextState() != newState;
        }
    }

}
