package com.rined.proposal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalField {

    @Field("name")
    @JsonProperty("name")
    private String name;

    @Field("description")
    @JsonProperty("description")
    private String description;

    @Field("value")
    @JsonProperty("value")
    private String value;
}
