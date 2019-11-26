package com.rined.template.actuator;

import com.rined.template.model.statistic.Statistic;
import com.rined.template.repositories.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Endpoint(id = "template-statistic")
public class TemplateStatistic {
    private final TemplateRepository repository;

    @ReadOperation
    public Statistic getStatistic() {
        final Statistic statistic = repository.countOfProvidersThatCreateTemplate();
        statistic.setTemplateCount(repository.count());
        return statistic;
    }
}