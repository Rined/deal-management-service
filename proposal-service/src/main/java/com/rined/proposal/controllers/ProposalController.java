package com.rined.proposal.controllers;

import com.rined.proposal.model.dto.ProposalRequestDto;
import com.rined.proposal.model.dto.ProposalRequestUpdateDto;
import com.rined.proposal.model.dto.ProviderDto;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;
import com.rined.proposal.resolver.Provider;
import com.rined.proposal.service.ProposalConsumerService;
import com.rined.proposal.service.ProposalService;
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
@Api(value = "/api", description = "Операции с предложениями")
public class ProposalController {
    private final ProposalService service;
    private final ProposalConsumerService consumerService;

    @GetMapping("/proposals")
    @ApiOperation(value = "Получить предложения поставщика")
    public List<Proposal> getAllProviderProposals(@ApiIgnore @Provider ProviderDto providerDto) {
        return service.getAllProposals(providerDto);
    }

    @GetMapping("/proposals/consumer/brief")
    @ApiOperation(value = "Получить все сокращенные предложения для потребителя")
    public List<ProposalBrief> getAllBriefProposals() {
        return consumerService.getBriefProposals();
    }

    @GetMapping("/proposals/brief")
    @ApiOperation(value = "Получить сокращенные предложения поставщика")
    public List<ProposalBrief> getAllBriefProviderProposals(@ApiIgnore @Provider ProviderDto providerDto) {
        return service.getAllBriefProposals(providerDto);
    }

    @GetMapping("/proposals/{proposalId}")
    @ApiOperation(value = "Получить предложение поставщика по id")
    public Proposal getProviderProposal(@PathVariable("proposalId") String proposalId,
                                        @ApiIgnore @Provider ProviderDto providerDto) {
        return service.getProposalById(proposalId, providerDto);
    }

    @DeleteMapping("/proposals/{proposalId}")
    @ApiOperation(value = "Удалить предложение поставщика по id")
    public void deleteProviderProposal(@PathVariable("proposalId") String proposalId,
                                       @ApiIgnore @Provider ProviderDto providerDto) {
        service.deleteById(proposalId, providerDto);
    }

    @PutMapping("/proposals/{proposalId}")
    @ApiOperation(value = "Изменить предложение поставщика по id")
    public void updateProviderProposal(@PathVariable("proposalId") String proposalId,
                                       @Valid @RequestBody ProposalRequestUpdateDto proposalDto,
                                       @ApiIgnore @Provider ProviderDto providerDto) {
        service.updateProposal(proposalId, proposalDto, providerDto);
    }

    @PostMapping("/proposals")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Создать предложение поставщика")
    public void createProviderProposal(@Valid @RequestBody ProposalRequestDto proposalDto,
                                       @ApiIgnore @Provider ProviderDto providerDto) {
        service.createProposal(proposalDto, providerDto);
    }
}
