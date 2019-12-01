package com.rined.deal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@RequiredArgsConstructor
public class DealRequestDto {
    @JsonProperty("proposalId")
    @NotBlank(message = "Proposal id is necessary")
    private String proposalId;

    @JsonProperty("proposalTitle")
    @NotBlank(message = "Proposal title is necessary")
    private String proposalTitle;

    @JsonProperty("dealSubject")
    @NotBlank(message = "Proposal subject is necessary")
    private String dealSubject;

    @JsonProperty("providerId")
    @NotBlank(message = "Provider id is necessary")
    private String providerId;
}
