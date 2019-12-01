package com.rined.deal.services;

import com.rined.deal.model.Deal;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.ProviderDto;

public interface DealStateService {

    Deal declineProvider(String dealId, ProviderDto provider);

    Deal declineConsumer(String dealId, ConsumerDto consumer);

}
