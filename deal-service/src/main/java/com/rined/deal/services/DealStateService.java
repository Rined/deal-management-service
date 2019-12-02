package com.rined.deal.services;

import com.rined.deal.model.Deal;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealConsumerRequestInfoDto;
import com.rined.deal.model.dto.DealRequestInfoDto;
import com.rined.deal.model.dto.ProviderDto;

public interface DealStateService {

    Deal declineProvider(String dealId, ProviderDto provider);

    Deal declineConsumer(String dealId, ConsumerDto consumer);

    Deal acceptProvider(String dealId, ProviderDto providerDto);

    Deal acceptConsumer(String dealId, ConsumerDto consumerDto);

    Deal requestInfoProvider(String dealId, ProviderDto providerDto, DealRequestInfoDto dealRequestInfoDto);

    Deal requestInfoConsumer(String dealId, ConsumerDto consumerDto, DealConsumerRequestInfoDto dealConsumerRequestInfoDto);

    Deal doneProvider(String dealId, ProviderDto providerDto);
}
