package com.rined.client.repositories;

import com.rined.client.model.DocumentTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateRepository extends MongoRepository<DocumentTemplate, String> {
}
