package com.rined.proposal.actuator;

import com.rined.proposal.model.statistic.Statistic;
import com.rined.proposal.repositories.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Endpoint(id = "proposal-statistic")
public class ProposalStatistic {
    private final ProposalRepository repository;

    @ReadOperation
    public Statistic getStatistic() {
        final Statistic statistic = repository.countOfUsersThatCreateProposal();
        statistic.setProposalCount(repository.count());
        return statistic;
    }
}
