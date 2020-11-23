package com.rined.deal.repositories;

import com.rined.deal.model.DealOutbox;
import com.rined.deal.model.OutboxStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OutboxRepository extends MongoRepository<DealOutbox, String>, DealRepositoryCustom {


    List<DealOutbox> findAllByOutboxStatus(OutboxStatus outboxStatus);

}
