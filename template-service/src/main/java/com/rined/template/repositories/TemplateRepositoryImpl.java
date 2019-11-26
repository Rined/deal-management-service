package com.rined.template.repositories;

import com.rined.template.model.statistic.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

@RequiredArgsConstructor
public class TemplateRepositoryImpl implements TemplateRepositoryCustom {
    private final MongoTemplate template;

    @Override
    public Statistic countOfUsersThatCreateTemplate() {
        Aggregation aggregation = Aggregation.newAggregation(
                group("authorId"),
                group().count().as("userCount")
        );
        return template.aggregate(aggregation, "template", Statistic.class)
                .getUniqueMappedResult();
    }
}