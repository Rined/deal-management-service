package com.rined.proposal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDtoField {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;

}
