package com.rined.deal.services;

import com.rined.deal.model.Deal;
import com.rined.deal.model.DealBrief;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealCreateRequestDto;
import com.rined.deal.model.dto.ProviderDto;

import java.util.List;

public interface DealService {

    Deal createDeal(DealCreateRequestDto dealDto, ConsumerDto consumerDto);

    List<DealBrief> consumerDeals(ConsumerDto consumerDto);

    List<DealBrief> providerDeals(ProviderDto providerDto);

    Deal getConsumerDealById(String dealId, ConsumerDto consumerDto);

    Deal getProviderDealById(String dealId, ProviderDto providerDto);
}
