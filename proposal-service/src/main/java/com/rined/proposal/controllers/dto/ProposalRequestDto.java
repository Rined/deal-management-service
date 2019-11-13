package com.rined.proposal.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.proposal.model.ProposalField;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class ProposalRequestDto {

    @NotNull
    @JsonProperty("name")
    @NotBlank(message = "Proposal name is mandatory")
    private String proposalName;

    @JsonProperty("fields")
    private List<ProposalField> fields;

    @Field("format")
    @JsonProperty("format")
    private String templateFormat;
}
