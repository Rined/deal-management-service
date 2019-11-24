package com.rined.proposal.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalField;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;

@SuppressWarnings({"unused"})
@ChangeLog(order = "000")
public class MongoDBChangeLog0 {

    @ChangeSet(order = "000", id = "dropDB", author = "rined", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initProposals", author = "rined", runAlways = true)
    public void initTemplates(MongoTemplate mongoTemplate) {
        mongoTemplate.save(
                new Proposal(
                        "Дом в центре леса",
                        Arrays.asList(
                                new ProposalField("object", "объект аренды", "Дом"),
                                new ProposalField("description", "описание", "Теплый, уютный"),
                                new ProposalField("type", "тип аренды", "Краткосрочная"),
                                new ProposalField("address", "адрес объекта аренды", "Центр леса"),
                                new ProposalField("price", "цена аренды", "1000")
                        ),
                        "## Аренда ${object}\r\n\r\n" +
                                "${description}\r\n\r\n" +
                                "### Тип аренды: ${type}\r\n\r\n" +
                                "### Адрес: ${address}\r\n\r\n" +
                                "### Цена: ${price}"
                )
        );
    }
}
