package com.rined.deal.repositories;

import com.rined.deal.model.DealBrief;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
public class DealRepositoryCustomImpl implements DealRepositoryCustom {
    private final MongoTemplate template;

    @Override
    public List<DealBrief> getAllBriefDealsForConsumer(String consumerId) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(where("consumerId").is(consumerId)),
                project()
                        .andInclude("_id")
                        .andInclude("state")
                        .and("info.dealTitle").as("title")
        );
        return template.aggregate(aggregation, "deal", DealBrief.class).getMappedResults();
    }

    @Override
    public List<DealBrief> getAllBriefDealsForProvider(String providerId) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(where("providerId").is(providerId)),
                project()
                        .andInclude("_id")
                        .andInclude("state")
                        .and("info.dealTitle").as("title")
        );
        return template.aggregate(aggregation, "deal", DealBrief.class).getMappedResults();
    }
}
