package com.rined.proposal.service;

import com.rined.proposal.controllers.dto.ProposalRequestDto;
import com.rined.proposal.controllers.dto.ProposalRequestUpdateDto;
import com.rined.proposal.controllers.dto.UserDto;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;

import java.util.List;

public interface ProposalService {

    List<Proposal> getAllProposals(UserDto userDto);

    Proposal getProposalById(String proposalId, UserDto userDto);

    void deleteById(String proposalId, UserDto userDto);

    void createProposal(ProposalRequestDto proposalDto, UserDto userDto);

    void updateProposal(String proposalId, ProposalRequestUpdateDto proposalDto, UserDto userDto);

    List<ProposalBrief> getAllBriefProposals(UserDto userDto);

}
