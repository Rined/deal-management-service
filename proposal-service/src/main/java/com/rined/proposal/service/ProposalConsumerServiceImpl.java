package com.rined.proposal.service;

import com.rined.proposal.model.ProposalBriefForConsumer;
import com.rined.proposal.model.dto.ConsumerDto;
import com.rined.proposal.model.dto.UserDto;
import com.rined.proposal.repositories.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProposalConsumerServiceImpl implements ProposalConsumerService {
    private final ProposalRepository repository;
    private final UserService userService;


    @Override
    public List<ProposalBriefForConsumer> getBriefProposals(ConsumerDto consumerDto) {
        List<ProposalBriefForConsumer> allBriefProposalsForConsumer = repository.getAllBriefProposalsForConsumer();

        List<String> ids = allBriefProposalsForConsumer.stream().map(ProposalBriefForConsumer::getProviderId)
                .collect(Collectors.toList());

        Map<String, UserDto> users = userService.getUsers(consumerDto, ids);

        for (ProposalBriefForConsumer proposal : allBriefProposalsForConsumer) {
            UserDto userDto = users.get(proposal.getProviderId());
            proposal.setUser(userDto);
        }

        return allBriefProposalsForConsumer;
    }


}
