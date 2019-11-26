package com.rined.proposal.controllers;

import com.rined.proposal.model.dto.ProposalRequestDto;
import com.rined.proposal.model.dto.ProposalRequestUpdateDto;
import com.rined.proposal.model.dto.UserDto;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;
import com.rined.proposal.resolver.User;
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
@Api(value = "/api", description = "Операции с предложениями пользователя")
public class ProposalController {
    private final ProposalService service;
    private final ProposalConsumerService consumerService;

    @GetMapping("/proposals")
    @ApiOperation(value = "Получить предложения пользователя")
    public List<Proposal> getAllTemplates(@ApiIgnore @User UserDto userDto) {
        return service.getAllProposals(userDto);
    }

    @GetMapping("/proposals/consumer/brief")
    @ApiOperation(value = "Получить все сокращенные предложения для потребителя")
    public List<ProposalBrief> getAllBriefTemplatesForConsumer() {
        return consumerService.getBriefProposals();
    }

    @GetMapping("/proposals/brief")
    @ApiOperation(value = "Получить сокращенные предложения пользователя")
    public List<ProposalBrief> getAllBriefTemplates(@ApiIgnore @User UserDto userDto) {
        return service.getAllBriefProposals(userDto);
    }

    @GetMapping("/proposals/{proposalId}")
    @ApiOperation(value = "Получить предложение пользователя по id")
    public Proposal getTemplateById(@PathVariable("proposalId") String proposalId,
                                    @ApiIgnore @User UserDto userDto) {
        return service.getProposalById(proposalId, userDto);
    }

    @DeleteMapping("/proposals/{proposalId}")
    @ApiOperation(value = "Удалить предложение пользователя по id")
    public void deleteById(@PathVariable("proposalId") String proposalId,
                           @ApiIgnore @User UserDto userDto) {
        service.deleteById(proposalId, userDto);
    }

    @PutMapping("/proposals/{proposalId}")
    @ApiOperation(value = "Изменить предложение пользователя по id")
    public void updateTemplate(@PathVariable("proposalId") String proposalId,
                               @Valid @RequestBody ProposalRequestUpdateDto proposalDto,
                               @ApiIgnore @User UserDto userDto) {
        service.updateProposal(proposalId, proposalDto, userDto);
    }

    @PostMapping("/proposals")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Создать предложение пользователя")
    public void createTemplate(@Valid @RequestBody ProposalRequestDto proposalDto,
                               @ApiIgnore @User UserDto userDto) {
        service.createProposal(proposalDto, userDto);
    }
}
