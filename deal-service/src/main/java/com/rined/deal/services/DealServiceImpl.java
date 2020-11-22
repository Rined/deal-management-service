package com.rined.deal.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rined.deal.converter.DealConverter;
import com.rined.deal.exception.AlreadyExistsException;
import com.rined.deal.exception.NotFoundException;
import com.rined.deal.model.Deal;
import com.rined.deal.model.DealBrief;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealCreateRequestDto;
import com.rined.deal.model.dto.ProviderDto;
import com.rined.deal.properties.AppProperties;
import com.rined.deal.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository repository;
    private final DealConverter converter;
    private final AppProperties properties;

    @Override
    public Deal createDeal(DealCreateRequestDto dealDto, ConsumerDto consumerDto) {
        Deal deal = converter.convertRequestDtoAndConsumerDtoToDeal(dealDto, consumerDto);
        if (repository.existsByIdAndConsumerIdAndProviderId(deal.getId(), deal.getConsumerId(), deal.getProviderId())) {
            throw new AlreadyExistsException("Deal already exists!");
        }
        return repository.save(deal);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<DealBrief> consumerDeals(ConsumerDto consumerDto) {
        return repository.getAllBriefDealsForConsumer(consumerDto.getId());
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public List<DealBrief> providerDeals(ProviderDto providerDto) {
        return repository.getAllBriefDealsForProvider(providerDto.getId());
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Deal getConsumerDealById(String dealId, ConsumerDto consumerDto) {
        return repository.findByIdAndConsumerId(dealId, consumerDto.getId()).orElseThrow(
                () -> new NotFoundException("Deal with id %s for consumer %s not found!",
                        dealId, consumerDto.getUsername()));
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Deal getProviderDealById(String dealId, ProviderDto providerDto) {
        return repository.findByIdAndProviderId(dealId, providerDto.getId()).orElseThrow(
                () -> new NotFoundException("Deal with id %s for provider %s not found!",
                        dealId, providerDto.getUsername()));
    }

}
