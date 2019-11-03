package com.rined.template.repositories;

import com.rined.template.model.Template;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TemplateRepositoryCustomImpl implements TemplateRepositoryCustom {
    private final MongoTemplate template;

    @Override
    public void removeFormatterFromArrayById(String id) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        Update update = new Update().pull("formatters", query);
        template.updateMulti(new Query(), update, Template.class);
    }
}
