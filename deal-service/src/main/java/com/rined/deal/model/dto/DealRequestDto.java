package com.rined.deal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class DealRequestDto {
    @JsonProperty("proposalId")
    private String proposalId;

    @JsonProperty("proposalTitle")
    private String proposalTitle;

    @JsonProperty("dealSubject")
    private String dealSubject;

    @JsonProperty("providerId")
    private String providerId;
}
