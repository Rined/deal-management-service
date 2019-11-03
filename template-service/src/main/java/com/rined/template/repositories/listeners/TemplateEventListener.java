package com.rined.template.repositories.listeners;

import com.rined.template.model.Template;
import com.rined.template.repositories.FormatterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@SuppressWarnings("NullableProblems")
public class TemplateEventListener extends AbstractMongoEventListener<Template> {
    private final FormatterRepository repository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Template> event) {
        Template template = event.getSource();
        template.getFormatters().stream()
                .filter(formatter -> Objects.isNull(formatter.getId()))
                .forEach(repository::save);
    }
}
