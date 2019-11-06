package com.rined.template.repositories;

import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TemplateRepository extends MongoRepository<Template, String> {

    @Query(value = "{}", fields = "{_id : 1, name : 1}")
    List<TemplateBrief> getAllBriefTemplates();
}
