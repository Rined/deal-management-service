package com.rined.proposal.service;

import com.rined.proposal.model.dto.SagaProposalResult;

public interface SagaProposalCooperationMember {

    void income(String message);

    void outcome(SagaProposalResult sagaDealResult);

}
