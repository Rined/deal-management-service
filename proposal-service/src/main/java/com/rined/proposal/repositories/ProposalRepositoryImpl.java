package com.rined.proposal.repositories;

import com.rined.proposal.model.statistic.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

@RequiredArgsConstructor
public class ProposalRepositoryImpl implements ProposalRepositoryCustom {
    private final MongoTemplate template;


    @Override
    public Statistic countOfUsersThatCreateProposal() {
        Aggregation aggregation = Aggregation.newAggregation(
                group("authorId"),
                group().count().as("userCount")
        );
        return template.aggregate(aggregation, "proposal", Statistic.class)
                .getUniqueMappedResult();
    }
}
