package com.rined.orchestrator.repositories;

import com.rined.orchestrator.model.DealSagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealSagaStatusRepository extends JpaRepository<DealSagaStatus, String> {
}
