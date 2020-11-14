package com.rined.proposal.service;

import com.rined.proposal.model.dto.ProposalRequestDto;
import com.rined.proposal.model.dto.ProposalRequestUpdateDto;
import com.rined.proposal.model.dto.ProviderDto;
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
    public List<Proposal> getAllProposals(ProviderDto providerDto) {
        return repository.findAllByProviderId(providerDto.getId());
    }

    @Override
    public Proposal getProposalById(String proposalId, ProviderDto providerDto) {
        return repository.findByIdAndProviderId(proposalId, providerDto.getId())
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", proposalId));
    }

    @Override
    public Proposal getProposalById(String proposalId) {
        return repository.findById(proposalId)
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", proposalId));
    }

    @Override
    public void deleteById(String proposalId, ProviderDto providerDto) {
        repository.deleteByIdAndProviderId(proposalId, providerDto.getId());
    }

    @Override
    public Proposal createProposal(ProposalRequestDto proposalDto, ProviderDto providerDto) {
        Proposal proposal = converter.requestDtoToBean(proposalDto, providerDto);
        return repository.save(proposal);
    }

    @Override
    public Proposal updateProposal(String proposalId, ProposalRequestUpdateDto proposalDto, ProviderDto providerDto) {
        Proposal proposal = repository.findByIdAndProviderId(proposalId, providerDto.getId())
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", proposalId));
        proposal.setProposalName(proposalDto.getProposalName());
        proposal.setFields(proposalDto.getFields());
        return repository.save(proposal);
    }

    @Override
    public List<ProposalBrief> getAllBriefProposals(ProviderDto providerDto) {
        return repository.getAllBriefProposals(providerDto.getId());
    }
}
