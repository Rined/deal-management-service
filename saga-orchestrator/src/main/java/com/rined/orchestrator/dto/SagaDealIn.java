package com.rined.orchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SagaDealIn {

    private String dealId;

    private String proposalId;

    private SagaDealRequestType type;

    private boolean result;
}
