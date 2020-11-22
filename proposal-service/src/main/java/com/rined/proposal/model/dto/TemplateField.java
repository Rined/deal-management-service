package com.rined.proposal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateField {

    @Field("name")
    @JsonProperty("name")
    private String fieldName;

    @Field("description")
    @JsonProperty("description")
    private String description;
}
