package com.rined.deal.services;

import com.rined.deal.model.dto.SagaDealResult;

public interface SagaDealCooperationMember {

    void income(String message);

    void outcome(SagaDealResult sagaDealResult);

}
