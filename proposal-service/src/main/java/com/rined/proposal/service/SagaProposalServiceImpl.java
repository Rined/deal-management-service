package com.rined.proposal.service;

import com.rined.proposal.converter.ProposalConverter;
import com.rined.proposal.model.AbortedProposal;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.dto.SagaProposalRequest;
import com.rined.proposal.repositories.AbortedProposalRepository;
import com.rined.proposal.repositories.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SagaProposalServiceImpl implements SagaProposalService {
    private final ProposalRepository proposalRepository;
    private final AbortedProposalRepository abortedProposalRepository;
    private final ProposalConverter proposalConverter;

    @Override
    @Transactional
    public void sagaApprove(SagaProposalRequest request) {
        Optional<Proposal> proposalOptional = proposalRepository.findById(request.getProposalId());
        if(!proposalOptional.isPresent())
            return;
        Proposal proposal = proposalOptional.get();
        AbortedProposal abortedProposal = proposalConverter.convertProposalToAborted(proposal);
        abortedProposalRepository.save(abortedProposal);
        proposalRepository.deleteById(request.getProposalId());
    }

    @Override
    @Transactional
    public void sagaReject(SagaProposalRequest request) {
        Optional<AbortedProposal> abortedProposalOptional = abortedProposalRepository.findById(request.getProposalId());
        if(!abortedProposalOptional.isPresent())
            return;
        AbortedProposal abortedProposal = abortedProposalOptional.get();
        Proposal proposal = proposalConverter.convertAbortedToProposal(abortedProposal);
        proposalRepository.save(proposal);
        abortedProposalRepository.deleteById(request.getProposalId());
    }
}
