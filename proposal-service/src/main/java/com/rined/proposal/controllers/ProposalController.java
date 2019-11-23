package com.rined.proposal.controllers;

import com.rined.proposal.controllers.dto.ProposalRequestDto;
import com.rined.proposal.controllers.dto.ProposalRequestUpdateDto;
import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;
import com.rined.proposal.controllers.dto.UserDto;
import com.rined.proposal.resolver.User;
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

    @GetMapping("/")
    public List<Proposal> getAllTemplates(@User UserDto userDto) {
        System.err.println(userDto);
        return service.getAllProposals();
    }

    @GetMapping("/brief")
    public List<ProposalBrief> getAllBriefTemplates() {
        return service.getAllBriefProposals();
    }

    @GetMapping("/{proposalId}")
    public Proposal getTemplateById(@PathVariable("proposalId") String proposalId) {
        return service.getProposalById(proposalId);
    }

    @DeleteMapping("/{proposalId}")
    public void deleteById(@PathVariable("proposalId") String proposalId) {
        service.deleteById(proposalId);
    }

    @PutMapping("/{proposalId}")
    public void updateTemplate(@PathVariable("proposalId") String proposalId,
                               @Valid @RequestBody ProposalRequestUpdateDto proposalDto) {
        service.updateProposal(proposalId, proposalDto);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTemplate(@Valid @RequestBody ProposalRequestDto proposalDto) {
        service.createProposal(proposalDto);
    }
}
