package com.rined.proposal.service;

import com.rined.proposal.controllers.dto.ProposalRequestDto;
import com.rined.proposal.controllers.dto.ProposalRequestUpdateDto;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;

import java.util.List;

public interface ProposalService {

    List<Proposal> getAllProposals();

    Proposal getProposalById(String proposalId);

    void deleteById(String proposalId);

    void createProposal(ProposalRequestDto proposalDto);

    void updateProposal(String proposalId, ProposalRequestUpdateDto proposalDto);

    List<ProposalBrief> getAllBriefProposals();

}
