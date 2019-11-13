package com.rined.proposal.service;

import com.rined.proposal.controllers.dto.ProposalRequestDto;
import com.rined.proposal.controllers.dto.ProposalRequestUpdateDto;
import com.rined.proposal.converter.ProposalConverter;
import com.rined.proposal.exception.NotFoundException;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;
import com.rined.proposal.repositories.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository repository;
    private final ProposalConverter converter;

    @Override
    public List<Proposal> getAllProposals() {
        return repository.findAll();
    }

    @Override
    public Proposal getProposalById(String proposalId) {
        return repository.findById(proposalId)
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", proposalId));
    }

    @Override
    public void deleteById(String proposalId) {
        repository.deleteById(proposalId);
    }

    @Override
    public void createProposal(ProposalRequestDto proposalDto) {
        Proposal proposal = converter.requestDtoToBean(proposalDto);
        repository.save(proposal);
    }

    @Override
    public void updateProposal(String proposalId, ProposalRequestUpdateDto proposalDto) {
        Proposal proposal = repository.findById(proposalId)
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", proposalId));
        proposal.setProposalName(proposalDto.getProposalName());
        proposal.setFields(proposalDto.getFields());
        repository.save(proposal);
    }

    @Override
    public List<ProposalBrief> getAllBriefProposals() {
        return repository.getAllBriefProposals();
    }
}
