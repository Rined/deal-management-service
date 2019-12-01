package com.rined.deal.services;

import com.rined.deal.converter.DealConveter;
import com.rined.deal.exception.NotFoundException;
import com.rined.deal.model.Deal;
import com.rined.deal.model.DealBrief;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealRequestDto;
import com.rined.deal.model.dto.ProviderDto;
import com.rined.deal.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository repository;
    private final DealConveter converter;

    @Override
    public void createDeal(DealRequestDto dealDto, ConsumerDto consumerDto) {
        Deal deal = converter.convertRequestDtoAndConsumerDtoToDeal(dealDto, consumerDto);
        repository.save(deal);
    }

    @Override
    public List<DealBrief> consumerDeals(ConsumerDto consumerDto) {
        return repository.getAllBriefDealsForConsumer(consumerDto.getId());
    }

    @Override
    public List<DealBrief> providerDeals(ProviderDto providerDto) {
        return repository.getAllBriefDealsForProvider(providerDto.getId());
    }

    @Override
    public Deal getConsumerDealById(String dealId, ConsumerDto consumerDto) {
        return repository.findByIdAndConsumerId(dealId, consumerDto.getId()).orElseThrow(
                () -> new NotFoundException("Deal with id %s for consumer %s not found!",
                        dealId, consumerDto.getUsername()
                )
        );
    }

    @Override
    public Deal getProviderDealById(String dealId, ProviderDto providerDto) {
        return repository.findByIdAndProviderId(dealId, providerDto.getId()).orElseThrow(
                () -> new NotFoundException("Deal with id %s for provider %s not found!",
                        dealId, providerDto.getUsername()
                )
        );
    }

}
