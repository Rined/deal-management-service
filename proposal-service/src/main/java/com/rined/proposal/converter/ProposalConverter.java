package com.rined.proposal.converter;

import com.rined.proposal.controllers.dto.ProposalRequestDto;
import com.rined.proposal.controllers.dto.UserDto;
import com.rined.proposal.model.Proposal;

public interface ProposalConverter {

    Proposal requestDtoToBean(ProposalRequestDto dto, UserDto userDto);

}
