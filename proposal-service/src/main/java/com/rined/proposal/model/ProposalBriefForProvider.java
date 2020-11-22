package com.rined.proposal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalBriefForProvider {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

}
