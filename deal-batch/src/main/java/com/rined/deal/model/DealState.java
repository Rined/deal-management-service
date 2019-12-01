package com.rined.deal.model;

public enum DealState {
    PROVIDER_DECLINE(null),
    CONSUMER_DECLINE(null),
    DONE(null),
    IN_WORK(DONE),
    CONSUMER_PROVIDE_INFO(IN_WORK),
    PROVIDER_REQUEST_INFO(CONSUMER_PROVIDE_INFO),
    CONSUMER_ACCEPT(PROVIDER_REQUEST_INFO),
    PROVIDER_ACCEPT(CONSUMER_ACCEPT);

    private final DealState nextState;

    DealState(DealState nextState) {
        this.nextState = nextState;
    }

    public DealState getNextState() {
        return nextState;
    }
}
