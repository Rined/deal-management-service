package com.rined.orchestrator.model;

public enum Phase {
    PHASE_1_PROPOSAL,
    PHASE_1_PROPOSAL_REJECT,
    PHASE_1_PROPOSAL_AWAIT,
    PHASE_2_DEAL,
    PHASE_2_DEAL_REJECT,
    PHASE_2_DEAL_AWAIT,
    PHASE_3_PAYMENT,
    PHASE_3_PAYMENT_AWAIT,
    PHASE_4_RETURN,
    DONE,
    FULLY_REJECTED,
    DIED
}

