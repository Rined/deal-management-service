package com.rined.deal.converter;

import com.rined.deal.model.Deal;
import com.rined.deal.model.DealInfo;
import com.rined.deal.model.DealState;
import com.rined.deal.model.FieldInfo;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealConsumerRequestInfoDto;
import com.rined.deal.model.dto.DealRequestDto;
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
    public Deal convertRequestDtoAndConsumerDtoToDeal(DealRequestDto dealDto, ConsumerDto consumerDto) {
        return new Deal(dealDto.getProviderId(), consumerDto.getId(), new DealInfo(dealDto.getProposalId(),
                dealDto.getProposalTitle(), dealDto.getDealSubject(), Collections.emptyList()),
                DealState.PROVIDER_ACCEPT
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
}
