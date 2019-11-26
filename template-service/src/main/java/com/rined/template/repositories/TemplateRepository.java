package com.rined.template.repositories;

import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository extends MongoRepository<Template, String>, TemplateRepositoryCustom {

    @Query(value = "{'authorId': :#{#authorId}}", fields = "{_id : 1, name : 1}")
    List<TemplateBrief> getAllBriefTemplates(@Param("authorId") String authorId);

    List<Template> findAllByAuthorId(String authorId);

    Optional<Template> findByIdAndAuthorId(String id, String authorId);

    void deleteByIdAndAuthorId(String id, String authorId);
}