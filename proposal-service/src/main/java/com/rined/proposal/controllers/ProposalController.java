package com.rined.proposal.controllers;

import com.rined.proposal.controllers.dto.ProposalRequestDto;
import com.rined.proposal.controllers.dto.ProposalRequestUpdateDto;
import com.rined.proposal.controllers.dto.UserDto;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;
import com.rined.proposal.resolver.User;
import com.rined.proposal.service.ProposalConsumerService;
import com.rined.proposal.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService service;
    private final ProposalConsumerService consumerService;

    @GetMapping("/")
    public List<Proposal> getAllTemplates(@User UserDto userDto) {
        return service.getAllProposals(userDto);
    }

    @GetMapping("/consumer/brief")
    public List<ProposalBrief> getAllBriefTemplatesForConsumer() {
        return consumerService.getBriefProposals();
    }

    @GetMapping("/brief")
    public List<ProposalBrief> getAllBriefTemplates(@User UserDto userDto) {
        return service.getAllBriefProposals(userDto);
    }

    @GetMapping("/{proposalId}")
    public Proposal getTemplateById(@PathVariable("proposalId") String proposalId,
                                    @User UserDto userDto) {
        return service.getProposalById(proposalId, userDto);
    }

    @DeleteMapping("/{proposalId}")
    public void deleteById(@PathVariable("proposalId") String proposalId,
                           @User UserDto userDto) {
        service.deleteById(proposalId, userDto);
    }

    @PutMapping("/{proposalId}")
    public void updateTemplate(@PathVariable("proposalId") String proposalId,
                               @Valid @RequestBody ProposalRequestUpdateDto proposalDto,
                               @User UserDto userDto) {
        service.updateProposal(proposalId, proposalDto, userDto);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTemplate(@Valid @RequestBody ProposalRequestDto proposalDto,
                               @User UserDto userDto) {
        service.createProposal(proposalDto, userDto);
    }
}
