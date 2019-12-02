package com.rined.proposal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.proposal.model.ProposalField;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class ProposalRequestUpdateDto {

    @NotNull
    @JsonProperty("name")
    @NotBlank(message = "Proposal name is mandatory")
    private String proposalName;

    @JsonProperty("fields")
    private List<ProposalField> fields;

}
