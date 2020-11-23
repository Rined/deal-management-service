package com.rined.deal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SagaDealResult {

    private String dealId;

    private String proposalId;

    private SagaDealRequestType type;

    private boolean result;

    public static SagaDealResult of(SagaDealRequest request, boolean result) {
        return new SagaDealResult(request.getDealId(), request.getProposalId(), request.getType(), result);
    }
}
