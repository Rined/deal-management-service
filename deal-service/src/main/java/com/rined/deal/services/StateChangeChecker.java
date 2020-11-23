package com.rined.deal.services;

import com.rined.deal.model.DealState;

public interface StateChangeChecker {

    boolean isInvalidStateChange(DealState currentState, DealState newState);

}
