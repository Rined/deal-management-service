package com.rined.orchestrator.repositories;

import com.rined.orchestrator.model.Phase;
import com.rined.orchestrator.model.SagaOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SagaOutboxRepository extends JpaRepository<SagaOutbox, String> {
    List<SagaOutbox> findAllByPhaseIn(List<Phase> in);
}
