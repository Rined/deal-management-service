package com.rined.client.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.rined.client.model.*;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

@ChangeLog(order = "000")
@SuppressWarnings({"unused"})
public class MongoDBChangeLog0 {

    private Documents documentsForFirstUser;
    private Documents documentsForSecondUser;

    @ChangeSet(order = "000", id = "dropDB", author = "rined", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initTemplates", author = "rined", runAlways = true)
    public void initTemplates(MongoTemplate mongoTemplate) {
        List<TemplateField> roomRentTemplateData = Arrays.asList(
                new TemplateField("lastName", "Family Name"),
                new TemplateField("firstName", "Name"),
                new TemplateField("phone", "Phone number"),
                new TemplateField("birthDate", "Your birthday date"),
                new TemplateField("address", "Registration address")
        );
        List<TemplateField> saleContractTemplateData = Arrays.asList(
                new TemplateField("lastName", "Family Name"),
                new TemplateField("firstName", "Name"),
                new TemplateField("address", "Registration address")
        );

        DocumentTemplate roomRentTemplate
                = mongoTemplate.save(new DocumentTemplate("Room rent", roomRentTemplateData));
        DocumentTemplate saleContractTemplate
                = mongoTemplate.save(new DocumentTemplate("Sale contract", saleContractTemplateData));
        FilledTemplateData filledTemplateData = new FilledTemplateData(roomRentTemplate,
                Arrays.asList(
                        new TemplateData("lastName", "Petrov"),
                        new TemplateData("firstName", "Anton"),
                        new TemplateData("phone", "0000000000"),
                        new TemplateData("birthDate", "29.11.1991"),
                        new TemplateData("address", "GG WP Street")
                )
        );
        List<FilledTemplateData> filledTemplateDataList = Collections.singletonList(filledTemplateData);

        documentsForFirstUser = new Documents(
                Arrays.asList(roomRentTemplate, saleContractTemplate),
                emptyList(),
                Arrays.asList(filledTemplateData, filledTemplateData)
        );

        documentsForSecondUser = new Documents(
                Collections.singletonList(saleContractTemplate),
                filledTemplateDataList,
                emptyList()
        );
    }


    @ChangeSet(order = "002", id = "initUsers", author = "rined", runAlways = true)
    public void initUsers(MongoTemplate template) {
        template.save(
                new User(
                        "Rined",
                        new UserInfo(
                                "test@test.ru",
                                "Anton",
                                "Petrov",
                                LocalDate.of(1991, 11, 29),
                                true
                        ),
                        documentsForFirstUser
                )
        );

        template.save(
                new User(
                        "NotRined",
                        new UserInfo(
                                "nottest@test.ru",
                                "Rine",
                                "Rined",
                                LocalDate.of(1991, 11, 29),
                                true
                        ),
                        documentsForSecondUser
                )
        );
    }
}
