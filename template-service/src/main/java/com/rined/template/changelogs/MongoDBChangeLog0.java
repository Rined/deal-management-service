package com.rined.template.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.rined.template.model.Template;
import com.rined.template.model.TemplateField;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;

@SuppressWarnings({"unused"})
@ChangeLog(order = "000")
public class MongoDBChangeLog0 {

    @ChangeSet(order = "000", id = "dropDB", author = "rined", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initTemplates", author = "rined", runAlways = true)
    public void initTemplates(MongoTemplate mongoTemplate) {
        mongoTemplate.save(
                new Template(
                        "Аренда",
                        Arrays.asList(
                                new TemplateField("object", "объект аренды"),
                                new TemplateField("description", "описание"),
                                new TemplateField("type", "тип аренды"),
                                new TemplateField("address", "адрес объекта аренды"),
                                new TemplateField("price", "цена аренды")
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
