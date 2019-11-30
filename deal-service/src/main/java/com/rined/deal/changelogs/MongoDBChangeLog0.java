package com.rined.deal.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.rined.deal.model.Deal;
import com.rined.deal.model.DealInfo;
import com.rined.deal.model.DealState;
import org.springframework.data.mongodb.core.MongoTemplate;

@SuppressWarnings({"unused"})
@ChangeLog(order = "000")
public class MongoDBChangeLog0 {

    @ChangeSet(order = "000", id = "dropDB", author = "rined", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initDeals", author = "rined", runAlways = true)
    public void initDeals(MongoTemplate mongoTemplate) {
        mongoTemplate.save(
                new Deal(
                        "asd",
                        "dsa",
                        new DealInfo(
                                "sdasasd",
                                "TITLE",
                                "TEXT!"
                        ),
                        DealState.WAIT_INFO
                )
        );
    }
}
