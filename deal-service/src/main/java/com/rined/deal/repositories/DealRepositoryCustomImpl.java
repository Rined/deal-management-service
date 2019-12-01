package com.rined.deal.repositories;

import com.rined.deal.model.Deal;
import com.rined.deal.model.DealBrief;
import com.rined.deal.model.DealState;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

    @Override
    public void updateState(String dealId, DealState state) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(dealId)));
        Update update = new Update();
        update.set("state", state);
        template.updateMulti(query, update, Deal.class);
    }
}
