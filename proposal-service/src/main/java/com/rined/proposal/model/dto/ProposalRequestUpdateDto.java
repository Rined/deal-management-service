package com.rined.proposal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.proposal.model.ProposalField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalRequestUpdateDto {

    @NotNull
    @JsonProperty("name")
    @NotBlank(message = "Proposal name is mandatory")
    private String proposalName;

    @JsonProperty("price")
    @NotNull(message = "Proposal price is mandatory")
    private Long proposalPrice;

    @JsonProperty("fields")
    private List<ProposalField> fields;

}
