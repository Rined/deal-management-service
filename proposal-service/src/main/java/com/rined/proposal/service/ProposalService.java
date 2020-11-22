package com.rined.proposal.service;

import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBriefForProvider;
import com.rined.proposal.model.dto.*;

import java.util.List;

public interface ProposalService {

    ProposalForConsumer getProposalById(ConsumerDto consumerDto, String proposalId);

    List<Proposal> getAllProposals(ProviderDto providerDto);

    Proposal getProposalById(String proposalId, ProviderDto providerDto);

    void deleteById(String proposalId, ProviderDto providerDto);

    Proposal createProposal(ProposalCreateRequestDto proposalDto, ProviderDto providerDto);

    Proposal updateProposal(String proposalId, ProposalRequestUpdateDto proposalDto, ProviderDto providerDto);

    List<ProposalBriefForProvider> getAllBriefProposals(ProviderDto providerDto);

}
