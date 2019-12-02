package com.rined.deal.mail.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.rined.deal.mail.model.DealTransformation;
import org.springframework.data.mongodb.core.MongoTemplate;

@SuppressWarnings({"unused"})
@ChangeLog(order = "000")
public class MongoDBChangeLog0 {

    @ChangeSet(order = "000", id = "dropDB", author = "rined", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initMailTemplates", author = "rined", runAlways = true)
    public void initUsers(MongoTemplate template) {
        template.save(new DealTransformation(
                "start",
                "Deal started",
                "Dear user %s your deals has been started!"
        ));
    }
}
