package com.rined.client.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final MongoTemplate mongoTemplate;
//
//    public List<TemplateIdNameDto> getTemplateIdName() {
//        Query query = new Query();
//        query.fields().include("_id").include("name");
//        return mongoTemplate.find(query, TemplateIdNameDto.class);
//    }
}
