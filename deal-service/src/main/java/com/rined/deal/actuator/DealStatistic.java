package com.rined.deal.actuator;

import com.rined.deal.model.statistic.Statistic;
import com.rined.deal.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Endpoint(id = "deal-statistic")
public class DealStatistic {
    private final DealRepository repository;

    @ReadOperation
    public Statistic getStatistic() {
        return new Statistic(repository.count());
    }
}
