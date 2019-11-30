package com.rined.deal.services;

import com.rined.deal.converter.DealConveter;
import com.rined.deal.model.Deal;
import com.rined.deal.model.DealBrief;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealRequestDto;
import com.rined.deal.model.dto.ProviderDto;
import com.rined.deal.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealService {
    private final DealRepository repository;
    private final DealConveter converter;

    @Transactional
    public void createDeal(DealRequestDto dealDto, ConsumerDto consumerDto) {
        Deal deal = converter.convertRequestDtoAndConsumerDtoToDeal(dealDto, consumerDto);
        repository.save(deal);
    }

    public List<DealBrief> consumerDeals(ConsumerDto consumerDto){
        return repository.getAllBriefDealsForConsumer(consumerDto.getId());
    }

    public List<DealBrief> providerDeals(ProviderDto providerDto){
        return repository.getAllBriefDealsForProvider(providerDto.getId());
    }

}
