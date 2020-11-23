package com.rined.deal.services;

import com.rined.deal.model.dto.SagaDealRequest;

public interface SagaDealService {

    void sagaApprove(SagaDealRequest request);

    void sagaReject(SagaDealRequest request);

}
