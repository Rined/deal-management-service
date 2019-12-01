package com.rined.deal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DealConsumerRequestInfoDto {

    @JsonProperty("fields")
    private List<FilledFieldDto> fields;

}
