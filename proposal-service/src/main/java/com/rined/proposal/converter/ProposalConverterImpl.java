package com.rined.proposal.converter;

import com.rined.proposal.model.AbortedProposal;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalField;
import com.rined.proposal.model.dto.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class ProposalConverterImpl implements ProposalConverter {

    @Override
    public Proposal requestDtoToBean(Template template, ProposalCreateRequestDto dto, ProviderDto providerDto) {
        Objects.requireNonNull(template);
        Objects.requireNonNull(dto);
        Objects.requireNonNull(providerDto);
        return new Proposal(
                dto.getProposalId(),
                dto.getProposalName(),
                dto.getProposalPrice(),
                merge(dto.getFields(), template.getFields()),
                template.getFormat(),
                providerDto.getId()
        );
    }

    @Override
    public ProposalForConsumer proposalToProposalForConsumer(Proposal proposal, UserDto userDto) {
        return new ProposalForConsumer(
                proposal.getId(),
                proposal.getProposalName(),
                proposal.getPrice(),
                proposal.getFields(),
                proposal.getTemplateFormat(),
                proposal.getProviderId(),
                userDto
        );
    }

    @Override
    public AbortedProposal convertProposalToAborted(Proposal proposal) {
        return new AbortedProposal(proposal.getId(), proposal.getProposalName(), proposal.getPrice(),
                proposal.getFields(), proposal.getTemplateFormat(), proposal.getProviderId());
    }

    @Override
    public Proposal convertAbortedToProposal(AbortedProposal abortedProposal) {
        return new Proposal(abortedProposal.getId(), abortedProposal.getProposalName(), abortedProposal.getPrice(),
                abortedProposal.getFields(), abortedProposal.getTemplateFormat(), abortedProposal.getProviderId());
    }

    private List<ProposalField> merge(List<ProposalDtoField> proposalFields, List<TemplateField> templateFields) {
        List<ProposalField> result = new ArrayList<>();
        for (ProposalDtoField proposalField : proposalFields) {
            for (TemplateField templateField : templateFields) {
                if (isEquals(proposalField, templateField)) {
                    result.add(createProposalField(proposalField, templateField));
                }
            }
        }
        return result;
    }

    private boolean isEquals(ProposalDtoField proposalField, TemplateField templateField) {
        if (proposalField == null || templateField == null)
            return false;
        return proposalField.getName().equals(templateField.getFieldName());
    }

    private ProposalField createProposalField(ProposalDtoField proposalField, TemplateField templateField) {
        return new ProposalField(templateField.getFieldName(), templateField.getDescription(), proposalField.getValue());
    }

}

