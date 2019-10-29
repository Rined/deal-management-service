package com.rined.client.repositories;

import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.model.collections.Template;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TemplateRepository extends MongoRepository<Template, String>, TemplateRepositoryCustom {

    @Query(value = "{}", fields = "{name : 1, _id : 1}")
    List<ResponseTemplateNameDto> getTemplateIdName();

}
