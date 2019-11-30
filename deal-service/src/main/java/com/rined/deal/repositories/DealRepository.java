package com.rined.deal.repositories;

import com.rined.deal.model.Deal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DealRepository extends MongoRepository<Deal, String>, DealRepositoryCustom  {

}
