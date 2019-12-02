package com.rined.proposal.converter;

import com.rined.proposal.model.dto.ProposalRequestDto;
import com.rined.proposal.model.dto.ProviderDto;
import com.rined.proposal.model.Proposal;

public interface ProposalConverter {

    Proposal requestDtoToBean(ProposalRequestDto dto, ProviderDto providerDto);

}
