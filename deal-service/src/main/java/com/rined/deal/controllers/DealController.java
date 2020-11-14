package com.rined.deal.controllers;

import com.rined.deal.model.Deal;
import com.rined.deal.model.DealBrief;
import com.rined.deal.model.dto.*;
import com.rined.deal.resolver.Consumer;
import com.rined.deal.resolver.Provider;
import com.rined.deal.services.DealService;
import com.rined.deal.services.DealStateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "/api", description = "Операции с сделками")
public class DealController {
    private final DealService service;
    private final DealStateService stateService;

    @PostMapping("/deals")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Создать сделку. Потребитель создает сделку.")
    public void createDeal(@Valid @RequestBody DealRequestDto dealDto,
                           @ApiIgnore @Consumer ConsumerDto consumerDto) {
        service.createDeal(dealDto, consumerDto);
    }

    @GetMapping("/deals/consumer/brief")
    public List<DealBrief> getConsumerDeals(@ApiIgnore @Consumer ConsumerDto consumerDto) {
        return service.consumerDeals(consumerDto);
    }

    @GetMapping("/deals/consumer/{dealId}")
    public Deal getConsumerDeal(@PathVariable("dealId") String dealId,
                                @ApiIgnore @Consumer ConsumerDto consumerDto) {
        return service.getConsumerDealById(dealId, consumerDto);
    }

    @GetMapping("/deals/provider/brief")
    public List<DealBrief> getProviderDeals(@ApiIgnore @Provider ProviderDto providerDto) {
        return service.providerDeals(providerDto);
    }

    @GetMapping("/deals/provider/{dealId}")
    public Deal getProviderDeal(@PathVariable("dealId") String dealId,
                                @ApiIgnore @Provider ProviderDto providerDto) {
        return service.getProviderDealById(dealId, providerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deals/consumer/{dealId}/decline")
    public Deal consumerDeclineDeal(@PathVariable("dealId") String dealId,
                                    @ApiIgnore @Consumer ConsumerDto consumerDto) {
        return stateService.declineConsumer(dealId, consumerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deals/consumer/{dealId}/accept")
    public Deal consumerAcceptDeal(@PathVariable("dealId") String dealId,
                                   @ApiIgnore @Consumer ConsumerDto consumerDto) {
        return stateService.acceptConsumer(dealId, consumerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deals/consumer/{dealId}/request")
    public Deal consumerRequestDeal(@PathVariable("dealId") String dealId,
                                    @ApiIgnore @Consumer ConsumerDto consumerDto,
                                    @Valid @RequestBody DealConsumerRequestInfoDto dealConsumerRequestInfoDto) {
        return stateService.requestInfoConsumer(dealId, consumerDto, dealConsumerRequestInfoDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deals/provider/{dealId}/decline")
    public Deal providerDeclineDeal(@PathVariable("dealId") String dealId,
                                    @ApiIgnore @Provider ProviderDto providerDto) {
        return stateService.declineProvider(dealId, providerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deals/provider/{dealId}/accept")
    public Deal providerAcceptDeal(@PathVariable("dealId") String dealId,
                                   @ApiIgnore @Provider ProviderDto providerDto) {
        return stateService.acceptProvider(dealId, providerDto);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deals/provider/{dealId}/request")
    public Deal providerRequestInfoDeal(@PathVariable("dealId") String dealId,
                                        @ApiIgnore @Provider ProviderDto providerDto,
                                        @Valid @RequestBody DealRequestInfoDto dealRequestInfoDto) {
        return stateService.requestInfoProvider(dealId, providerDto, dealRequestInfoDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deals/provider/{dealId}/done")
    public Deal providerDoneDeal(@PathVariable("dealId") String dealId,
                                 @ApiIgnore @Provider ProviderDto providerDto) {
        return stateService.doneProvider(dealId, providerDto);
    }
}
