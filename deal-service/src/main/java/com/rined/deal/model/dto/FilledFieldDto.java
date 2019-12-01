package com.rined.deal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FilledFieldDto {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("description")
    private String description;

    @JsonProperty("value")
    private String value;

}
