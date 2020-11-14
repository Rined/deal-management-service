package com.rined.proposal.converter;

import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.dto.ProposalRequestDto;
import com.rined.proposal.model.dto.ProviderDto;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ProposalConverterImpl implements ProposalConverter {

    @Override
    public Proposal requestDtoToBean(ProposalRequestDto dto, ProviderDto providerDto) {
        Objects.requireNonNull(dto);
        Objects.requireNonNull(providerDto);
        return new Proposal(dto.getProposalName(), dto.getFields(), dto.getTemplateFormat(), providerDto.getId());
    }

}
