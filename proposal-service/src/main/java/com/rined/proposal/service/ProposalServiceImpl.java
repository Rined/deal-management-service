package com.rined.proposal.service;

import com.rined.proposal.model.dto.ProposalRequestDto;
import com.rined.proposal.model.dto.ProposalRequestUpdateDto;
import com.rined.proposal.model.dto.UserDto;
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
    public List<Proposal> getAllProposals(UserDto userDto) {
        return repository.findAllByAuthorId(userDto.getId());
    }

    @Override
    public Proposal getProposalById(String proposalId, UserDto userDto) {
        return repository.findByIdAndAuthorId(proposalId, userDto.getId())
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", proposalId));
    }

    @Override
    public void deleteById(String proposalId, UserDto userDto) {
        repository.deleteByIdAndAuthorId(proposalId, userDto.getId());
    }

    @Override
    public void createProposal(ProposalRequestDto proposalDto, UserDto userDto) {
        Proposal proposal = converter.requestDtoToBean(proposalDto, userDto);
        repository.save(proposal);
    }

    @Override
    public void updateProposal(String proposalId, ProposalRequestUpdateDto proposalDto, UserDto userDto) {
        Proposal proposal = repository.findByIdAndAuthorId(proposalId, userDto.getId())
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", proposalId));
        proposal.setProposalName(proposalDto.getProposalName());
        proposal.setFields(proposalDto.getFields());
        repository.save(proposal);
    }

    @Override
    public List<ProposalBrief> getAllBriefProposals(UserDto userDto) {
        return repository.getAllBriefProposals(userDto.getId());
    }
}
