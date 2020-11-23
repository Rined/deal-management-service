package com.rined.deal.services;

import com.rined.deal.model.Deal;
import com.rined.deal.model.DealState;

public interface DealTransactionalOutbox {

    Deal persistDeal(String dealId, String consumerId, DealState newState);

}
