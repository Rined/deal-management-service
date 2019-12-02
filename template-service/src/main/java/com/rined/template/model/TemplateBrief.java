package com.rined.template.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateBrief {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

}
