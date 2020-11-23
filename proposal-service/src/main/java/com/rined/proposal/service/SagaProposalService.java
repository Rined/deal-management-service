package com.rined.proposal.service;

import com.rined.proposal.model.dto.SagaProposalRequest;

public interface SagaProposalService {

    void sagaApprove(SagaProposalRequest request);

    void sagaReject(SagaProposalRequest request);

}
