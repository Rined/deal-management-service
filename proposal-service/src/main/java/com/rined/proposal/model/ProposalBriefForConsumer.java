package com.rined.proposal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rined.proposal.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalBriefForConsumer {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("providerId")
    private String providerId;

    @JsonProperty("user")
    private UserDto user;

}
