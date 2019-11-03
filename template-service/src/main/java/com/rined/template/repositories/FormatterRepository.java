package com.rined.template.repositories;

import com.rined.template.model.Formatter;
import com.rined.template.model.FormatterBrief;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FormatterRepository extends MongoRepository<Formatter, String>  {

    List<Formatter> findAllById(List<String> ids);

    @Query(value = "{}", fields = "{_id : 1, name : 1}")
    List<FormatterBrief> getBriefFormatters();

}
