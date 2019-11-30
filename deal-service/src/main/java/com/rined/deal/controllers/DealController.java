package com.rined.deal.controllers;

import com.rined.deal.model.DealBrief;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealRequestDto;
import com.rined.deal.model.dto.ProviderDto;
import com.rined.deal.resolver.Consumer;
import com.rined.deal.resolver.Provider;
import com.rined.deal.services.DealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "/api", description = "Операции с сделками")
public class DealController {
    private final DealService service;

    @PostMapping("/deals")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Создать сделку. Потребитель создает сделку.")
    public void createProviderTemplate(@Valid @RequestBody DealRequestDto dealDto,
                                       @ApiIgnore @Consumer ConsumerDto consumerDto) {
        service.createDeal(dealDto, consumerDto);
    }

    @GetMapping("/deals/consumer/brief")
    public List<DealBrief> getConsumerDeals(@ApiIgnore @Consumer ConsumerDto consumerDto) {
        return service.consumerDeals(consumerDto);
    }

    @GetMapping("/deals/provider/brief")
    public List<DealBrief> getProviderDeals(@ApiIgnore @Provider ProviderDto providerDto) {
        return service.providerDeals(providerDto);
    }
}
