package com.rined.client.repositories;

import com.rined.client.dto.response.ResponseUserBrief;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Fields.field;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final MongoTemplate template;

    @Override
    public List<ResponseUserBrief> getBriefUsers() {
        Aggregation aggregation = Aggregation.newAggregation(
                project().and("id").as("id")
                        .and("info.firstName").concat(" ", field("info.secondName")).as("name")
                        .and("info.company").as("company")
        );
        return template.aggregate(aggregation, "user", ResponseUserBrief.class).getMappedResults();
    }
}
