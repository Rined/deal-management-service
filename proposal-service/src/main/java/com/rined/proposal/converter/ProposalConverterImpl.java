package com.rined.proposal.converter;

import com.rined.proposal.model.dto.ProposalRequestDto;
import com.rined.proposal.model.dto.UserDto;
import com.rined.proposal.model.Proposal;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ProposalConverterImpl implements ProposalConverter {
    @Override
    public Proposal requestDtoToBean(ProposalRequestDto dto, UserDto userDto) {
        Objects.requireNonNull(dto);
        Objects.requireNonNull(userDto);
        return new Proposal(
                dto.getProposalName(),
                dto.getFields(),
                dto.getTemplateFormat(),
                userDto.getId()
        );
    }
}
