package com.rined.deal.converter;

import com.rined.deal.model.*;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealConsumerRequestInfoDto;
import com.rined.deal.model.dto.DealCreateRequestDto;
import com.rined.deal.model.dto.DealRequestInfoDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DealConverterImpl implements DealConverter {

    @Override
    public Deal convertRequestDtoAndConsumerDtoToDeal(DealCreateRequestDto dealDto, ConsumerDto consumerDto) {
        return new Deal(dealDto.getId(), dealDto.getProviderId(), consumerDto.getId(),
                new DealInfo(dealDto.getProposalId(), dealDto.getProposalTitle(), dealDto.getDealSubject(), Collections.emptyList()),
                DealState.PROVIDER_ACCEPT,
                dealDto.getPrice()
        );
    }

    @Override
    public List<FieldInfo> convertProviderRequestInfo(DealRequestInfoDto dealRequestInfoDto) {
        return dealRequestInfoDto.getFields()
                .stream()
                .filter(Objects::nonNull)
                .filter(str -> !str.getDescription().isEmpty())
                .map(fieldDto -> new FieldInfo(
                        UUID.randomUUID().toString(),
                        fieldDto.getDescription()
                )).collect(Collectors.toList());
    }

    @Override
    public List<FieldInfo> convertConsumerRequestInfo(DealConsumerRequestInfoDto dealConsumerRequestInfoDto) {
        return dealConsumerRequestInfoDto.getFields()
                .stream()
                .filter(Objects::nonNull)
                .filter(str -> !str.getDescription().isEmpty())
                .map(fieldDto -> new FieldInfo(
                        fieldDto.getUuid(),
                        fieldDto.getDescription(),
                        fieldDto.getValue()
                )).collect(Collectors.toList());
    }

    @Override
    public List<AbortedDeal> convertDealToAborted(List<Deal> deals, String exceptId) {
        return deals.stream().filter(deal -> !deal.getId().equals(exceptId))
                .map(deal -> new AbortedDeal(deal.getId(), deal.getProviderId(), deal.getConsumerId(),
                        deal.getDealInfo(), deal.getState(), deal.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Deal> convertAbortedToDeal(List<AbortedDeal> abortedDeals) {
        return abortedDeals.stream()
                .map(abortedDeal -> new Deal(abortedDeal.getId(), abortedDeal.getProviderId(),
                        abortedDeal.getConsumerId(), abortedDeal.getDealInfo(), abortedDeal.getState(),
                        abortedDeal.getPrice()))
                .collect(Collectors.toList());
    }
}
