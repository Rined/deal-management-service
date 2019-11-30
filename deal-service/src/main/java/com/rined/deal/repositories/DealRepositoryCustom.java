package com.rined.deal.repositories;

import com.rined.deal.model.DealBrief;

import java.util.List;

public interface DealRepositoryCustom {

    List<DealBrief> getAllBriefDealsForConsumer(String consumerId);

    List<DealBrief> getAllBriefDealsForProvider(String providerId);

}
