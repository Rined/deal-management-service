package com.rined.deal.converter;

import com.rined.deal.model.Deal;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealRequestDto;

public interface DealConveter {

    Deal convertRequestDtoAndConsumerDtoToDeal(DealRequestDto dealDto, ConsumerDto consumerDto);

}
