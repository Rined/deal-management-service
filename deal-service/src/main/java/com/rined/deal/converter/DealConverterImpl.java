package com.rined.deal.converter;

import com.rined.deal.converter.DealConveter;
import com.rined.deal.model.Deal;
import com.rined.deal.model.DealInfo;
import com.rined.deal.model.DealState;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DealConverterImpl implements DealConveter {
    @Override
    public Deal convertRequestDtoAndConsumerDtoToDeal(DealRequestDto dealDto, ConsumerDto consumerDto) {
        return new Deal(
                dealDto.getProviderId(),
                consumerDto.getId(),
                new DealInfo(
                        dealDto.getProposalId(),
                        dealDto.getProposalTitle(),
                        dealDto.getDealSubject()
                ),
                DealState.WAIT_PROVIDER
        );
    }
}
