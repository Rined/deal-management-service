package com.rined.proposal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.proposal.model.ProposalField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
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
    @NotNull(message = "Proposal format is mandatory")
    @NotBlank(message = "Proposal format is mandatory")
    private String templateFormat;
}
