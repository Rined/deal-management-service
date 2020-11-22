package com.rined.proposal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.proposal.model.ProposalField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProposalForConsumer {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String proposalName;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("fields")
    private List<ProposalField> fields;

    @JsonProperty("format")
    private String templateFormat;

    @JsonProperty("providerId")
    private String providerId;

    @JsonProperty("user")
    private UserDto user;

}
