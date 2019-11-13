package com.rined.proposal.converter;

import com.rined.proposal.controllers.dto.ProposalRequestDto;
import com.rined.proposal.model.Proposal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
@SuppressWarnings("UnmappedTargetProperties")
public interface ProposalConverter {

    @Mappings({
            @Mapping(target = "proposalName", source = "dto.proposalName"),
            @Mapping(target = "templateFormat", source = "dto.templateFormat"),
            @Mapping(target = "fields", source = "dto.fields")
    })
    Proposal requestDtoToBean(ProposalRequestDto dto);

}
