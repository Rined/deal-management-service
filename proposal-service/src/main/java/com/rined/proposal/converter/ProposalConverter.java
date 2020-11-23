package com.rined.proposal.converter;

import com.rined.proposal.model.AbortedProposal;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.dto.*;

public interface ProposalConverter {

    Proposal requestDtoToBean(Template template, ProposalCreateRequestDto dto, ProviderDto providerDto);

    ProposalForConsumer proposalToProposalForConsumer(Proposal proposal, UserDto userDto);

    AbortedProposal convertProposalToAborted(Proposal proposal);

    Proposal convertAbortedToProposal(AbortedProposal abortedProposal);
}
