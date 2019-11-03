package com.rined.template.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.rined.template.model.Formatter;
import com.rined.template.model.Template;
import com.rined.template.model.TemplateField;
import com.rined.template.model.FormatterTypes;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@SuppressWarnings({"unused"})
@ChangeLog(order = "000")
public class MongoDBChangeLog0 {

    private Formatter formatter;
    private final String uuidName = UUID.randomUUID().toString();
    private final String uuidFamily = UUID.randomUUID().toString();
    private final String uuidAddress = UUID.randomUUID().toString();

    @ChangeSet(order = "000", id = "dropDB", author = "rined", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initDataTemplates", author = "rined", runAlways = true)
    public void initDataTemplates(MongoTemplate mongoTemplate) {
        formatter = mongoTemplate.save(
                new Formatter("Simple Template",
                        String.format(
                                "<!DOCTYPE html>" +
                                        "<html lang=\"en\">" +
                                        "<head>" +
                                        "    <meta charset=\"UTF-8\">" +
                                        "    <title>Simple template</title>" +
                                        "</head>" +
                                        "<body>" +
                                        "<div>" +
                                        "    <span>Name:</span> <span>${%s}</span>" +
                                        "</div>" +
                                        "<div>" +
                                        "    <span>Family:</span> <span>${%s}</span>" +
                                        "</div>" +
                                        "<div>" +
                                        "    <span>address:</span> <span>${%s}</span>" +
                                        "</div>" +
                                        "</body>" +
                                        "</html>",
                                uuidName, uuidFamily, uuidAddress
                        ), FormatterTypes.HTML
                ));
    }

    @ChangeSet(order = "002", id = "initTemplates", author = "rined", runAlways = true)
    public void initTemplates(MongoTemplate mongoTemplate) {
        mongoTemplate.save(
                new Template(
                        "Rent template",
                        Arrays.asList(
                                new TemplateField(uuidName, "Your name"),
                                new TemplateField(uuidFamily, "Your family"),
                                new TemplateField(uuidAddress, "Your address")
                        ),
                        Collections.singletonList(formatter)
                )
        );
    }
}
