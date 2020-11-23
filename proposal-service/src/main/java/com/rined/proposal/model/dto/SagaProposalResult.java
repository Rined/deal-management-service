package com.rined.proposal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SagaProposalResult {

    private String dealId;

    private String proposalId;

    private SagaProposalRequestType type;

    private boolean result;

    public static SagaProposalResult of(SagaProposalRequest request, boolean result) {
        return new SagaProposalResult(request.getDealId(), request.getProposalId(), request.getType(), result);
    }
}
