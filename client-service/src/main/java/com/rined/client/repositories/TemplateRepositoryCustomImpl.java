package com.rined.client.repositories;

import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.model.DocumentTemplate;
import com.rined.client.model.FilledTemplateData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ObjectOperators.ObjectToArray.valueOfToArray;

@Repository
@RequiredArgsConstructor
public class TemplateRepositoryCustomImpl implements TemplateRepositoryCustom {
    private final MongoTemplate template;

    //db.user.aggregate([
    //  {$match: {_id: new ObjectId("5db73776c3918936d816e7cf")}},
    //  {$project: {_id: 0, active: "$documents.active"}},
    //  {$unwind: "$active"},
    //  {$project: {templateMap: {$objectToArray: "$active"}}},
    //  {$project: {templateId: {$arrayElemAt: ["$templateMap.v", 1]}}},
    //  {$lookup: {from: "template", localField: "templateId", foreignField: "_id", as: "template"}},
    //  {$unwind: "$template"},
    //  {$project: {id: "$template._id", name: "$template.name"}}
    //]).pretty();
    @Override
    public List<ResponseTemplateNameDto> userActiveTemplateNameList(String userId) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("_id").is(userId)),
                project().andExclude("_id").and("documents.active").as("active"),
                unwind("active"),
                project().and(valueOfToArray("active")).as("templateMap"),
                project().and("templateMap.v").arrayElementAt(1).as("templateId"),
                lookup("template", "templateId", "_id", "template"),
                unwind("$template"),
                project()
                        .and("template._id").as("_id")
                        .and("template.name").as("name")

        );
        return template.aggregate(aggregation, "user", ResponseTemplateNameDto.class).getMappedResults();
    }

    //db.user.aggregate([
    //  {$match: {_id: new ObjectId("5db73a92c3918936ac760523")}},
    //  {$project: {_id: 0, active: "$documents.active"}},
    //  {$unwind: "$active"},
    //  {$project: {templateMap: {$objectToArray: "$active"}}},
    //  {$project: {templateId: {$arrayElemAt: ["$templateMap.v", 1]}}},
    //  {$lookup: {from: "template", localField: "templateId", foreignField: "_id", as: "template"}},
    //  {$unwind: "$template"},
    //  {$project: {_id: "$template._id", name: "$template.name", fields: "$template.fields"}}
    //]).pretty();
    @Override
    public List<DocumentTemplate> userActiveTemplateList(String userId) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("_id").is(userId)),
                project().andExclude("_id").and("documents.active").as("active"),
                unwind("active"),
                project().and(valueOfToArray("active")).as("templateMap"),
                project().and("templateMap.v").arrayElementAt(1).as("templateId"),
                lookup("template", "templateId", "_id", "template"),
                unwind("$template"),
                project()
                        .and("template._id").as("_id")
                        .and("template.name").as("name")
                        .and("template.fields").as("fields")

        );
        return template.aggregate(aggregation, "user", DocumentTemplate.class).getMappedResults();
    }

    @Override
    public List<FilledTemplateData> completedFilledTemplates(String userId) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("_id").is(userId)),
                project().and("documents.completed.templateRef").as("templateRef")
                        .and("documents.completed.value").as("value")

        );
        return template.aggregate(aggregation, "user", FilledTemplateData.class).getMappedResults();
    }

    @Override
    public List<FilledTemplateData> sentFilledTemplates(String userId) {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("_id").is(userId)),
                project().and("documents.sent.templateRef").as("templateRef")
                        .and("documents.sent.value").as("value")

        );
        return template.aggregate(aggregation, "user", FilledTemplateData.class).getMappedResults();
    }
}
