package com.rined.proposal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProposalCreateRequestDto {

    @JsonProperty("id")
    @NotNull(message = "Proposal id is mandatory")
    @NotBlank(message = "Proposal id is mandatory")
    private String proposalId;

    @JsonProperty("templateId")
    @NotNull(message = "Proposal templateId is mandatory")
    @NotBlank(message = "Proposal templateId is mandatory")
    private String templateId;

    @JsonProperty("name")
    @NotNull(message = "Proposal name is mandatory")
    @NotBlank(message = "Proposal name is mandatory")
    private String proposalName;

    @JsonProperty("price")
    @NotNull(message = "Proposal price is mandatory")
    private Long proposalPrice;

    @JsonProperty("fields")
    private List<ProposalDtoField> fields;

}
