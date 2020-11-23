package com.rined.orchestrator.service;

import com.rined.orchestrator.model.DealSagaStatus;
import com.rined.orchestrator.model.Phase;

public interface OutboxService {

    void saveToOutbox(DealSagaStatus dealSagaStatus, Phase phase);

}
