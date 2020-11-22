package com.rined.proposal.service;

import com.rined.proposal.converter.ProposalConverter;
import com.rined.proposal.exception.AlreadyExistsException;
import com.rined.proposal.exception.NotFoundException;
import com.rined.proposal.feign.TemplateFeignClient;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBriefForProvider;
import com.rined.proposal.model.dto.*;
import com.rined.proposal.repositories.ProposalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository repository;
    private final ProposalConverter converter;
    private final TemplateFeignClient templateClient;
    private final UserService userService;

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
    public ProposalForConsumer getProposalById(ConsumerDto consumerDto, String proposalId) {
        Proposal proposal = repository.findById(proposalId)
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", proposalId));
        log.info("Proposal {}", proposal);
        UserDto user = userService.getUser(consumerDto, proposal.getProviderId());
        log.info("User {}", user);
        ProposalForConsumer proposalForConsumer = converter.proposalToProposalForConsumer(proposal, user);
        log.info("ProposalForConsumer {}", proposalForConsumer);
        return proposalForConsumer;
    }

    @Override
    public void deleteById(String proposalId, ProviderDto providerDto) {
        repository.deleteByIdAndProviderId(proposalId, providerDto.getId());
    }

    @Override
    @Transactional
    public Proposal createProposal(ProposalCreateRequestDto proposalDto, ProviderDto providerDto) {
        if (repository.existsByIdAndProviderId(proposalDto.getProposalId(), providerDto.getId())) {
            throw new AlreadyExistsException("Proposal already exists!");
        }
        Template template = templateClient.getTemplateById(providerDto.getId(), providerDto.getUsername(),
                providerDto.getEmail(), proposalDto.getTemplateId());
        Proposal proposal = converter.requestDtoToBean(template, proposalDto, providerDto);
        return repository.save(proposal);
    }

    @Override
    @Transactional
    public Proposal updateProposal(String proposalId, ProposalRequestUpdateDto proposalDto, ProviderDto providerDto) {
        Proposal proposal = repository.findByIdAndProviderId(proposalId, providerDto.getId())
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", proposalId));
        proposal.setProposalName(proposalDto.getProposalName());
        proposal.setPrice(proposalDto.getProposalPrice());
        proposal.setFields(proposalDto.getFields());
        return repository.save(proposal);
    }

    @Override
    public List<ProposalBriefForProvider> getAllBriefProposals(ProviderDto providerDto) {
        return repository.getAllBriefProposals(providerDto.getId());
    }
}
