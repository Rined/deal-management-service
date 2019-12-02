package com.rined.deal.mail.repositories;

import com.rined.deal.mail.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
}
