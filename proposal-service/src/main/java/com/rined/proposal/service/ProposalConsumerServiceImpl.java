package com.rined.proposal.service;

import com.rined.proposal.model.ProposalBrief;
import com.rined.proposal.repositories.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProposalConsumerServiceImpl implements ProposalConsumerService {
    private final ProposalRepository repository;

    @Override
    public List<ProposalBrief> getBriefProposals() {
        return repository.getAllBriefProposalsForConsumer();
    }
}
