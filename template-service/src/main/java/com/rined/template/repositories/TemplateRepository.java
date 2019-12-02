package com.rined.template.repositories;

import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository extends MongoRepository<Template, String>, TemplateRepositoryCustom {

    @Query(value = "{'providerId': :#{#providerId}}", fields = "{_id : 1, name : 1}")
    List<TemplateBrief> getAllBriefTemplates(@Param("providerId") String providerId);

    List<Template> findAllByProviderId(String providerId);

    Optional<Template> findByIdAndProviderId(String id, String providerId);

    void deleteByIdAndProviderId(String id, String providerId);
}