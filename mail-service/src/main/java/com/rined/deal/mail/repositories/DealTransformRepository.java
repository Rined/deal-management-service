package com.rined.deal.mail.repositories;

import com.rined.deal.mail.model.DealTransformation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DealTransformRepository extends MongoRepository<DealTransformation, String> {

    Optional<DealTransformation> findByAction(String action);

}
