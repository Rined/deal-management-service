package com.rined.deal.converter;

import com.rined.deal.model.Deal;
import com.rined.deal.model.FieldInfo;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealConsumerRequestInfoDto;
import com.rined.deal.model.dto.DealRequestDto;
import com.rined.deal.model.dto.DealRequestInfoDto;

import java.util.List;

public interface DealConverter {

    Deal convertRequestDtoAndConsumerDtoToDeal(DealRequestDto dealDto, ConsumerDto consumerDto);

    List<FieldInfo> convertProviderRequestInfo(DealRequestInfoDto dealRequestInfoDto);

    List<FieldInfo> convertConsumerRequestInfo(DealConsumerRequestInfoDto dealConsumerRequestInfoDto);

}
