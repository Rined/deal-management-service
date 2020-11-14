package com.rined.proposal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.rined.proposal.model.dto.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document("proposal")
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {

    @Id
    @JsonProperty("id")
    @JsonView(Views.Provider.class)
    private String id;

    @Field("name")
    @JsonProperty("name")
    @JsonView(Views.Provider.class)
    private String proposalName;

    @Field("fields")
    @JsonProperty("fields")
    @JsonView(Views.Provider.class)
    private List<ProposalField> fields;

    @Field("format")
    @JsonProperty("format")
    @JsonView(Views.Provider.class)
    private String templateFormat;

    @Field("providerId")
    @JsonView(Views.Consumer.class)
    private String providerId;

    public Proposal(String proposalName, List<ProposalField> fields, String templateFormat, String providerId) {
        this.proposalName = proposalName;
        this.fields = fields;
        this.templateFormat = templateFormat;
        this.providerId = providerId;
    }
}
