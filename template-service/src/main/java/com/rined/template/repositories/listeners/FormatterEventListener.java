package com.rined.template.repositories.listeners;

import com.rined.template.model.Formatter;
import com.rined.template.repositories.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@SuppressWarnings("NullableProblems")
public class FormatterEventListener extends AbstractMongoEventListener<Formatter> {

    private final TemplateRepository repository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Formatter> event) {
        Document source = event.getSource();
        String id = source.get("_id").toString();
        repository.removeFormatterFromArrayById(id);
    }
}
