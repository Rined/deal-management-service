package com.rined.proposal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String id;

    @Field("name")
    @JsonProperty("name")
    private String proposalName;

    @Field("fields")
    @JsonProperty("fields")
    private List<ProposalField> fields;

    @Field("format")
    @JsonProperty("format")
    private String templateFormat;

    public Proposal(String proposalName, List<ProposalField> fields, String templateFormat) {
        this.proposalName = proposalName;
        this.fields = fields;
        this.templateFormat = templateFormat;
    }
}
