package com.rined.proposal.service;

import com.rined.proposal.model.dto.ProposalRequestDto;
import com.rined.proposal.model.dto.ProposalRequestUpdateDto;
import com.rined.proposal.model.dto.ProviderDto;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;

import java.util.List;

public interface ProposalService {

    Proposal getProposalById(String proposalId);

    List<Proposal> getAllProposals(ProviderDto providerDto);

    Proposal getProposalById(String proposalId, ProviderDto providerDto);

    void deleteById(String proposalId, ProviderDto providerDto);

    void createProposal(ProposalRequestDto proposalDto, ProviderDto providerDto);

    void updateProposal(String proposalId, ProposalRequestUpdateDto proposalDto, ProviderDto providerDto);

    List<ProposalBrief> getAllBriefProposals(ProviderDto providerDto);

}
