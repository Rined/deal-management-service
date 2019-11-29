package com.rined.proposal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.rined.proposal.model.dto.Views;
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
    @JsonView(Views.Provider.class)
    private String name;

    @Field("description")
    @JsonProperty("description")
    @JsonView(Views.Provider.class)
    private String description;

    @Field("value")
    @JsonProperty("value")
    @JsonView(Views.Provider.class)
    private String value;
}
