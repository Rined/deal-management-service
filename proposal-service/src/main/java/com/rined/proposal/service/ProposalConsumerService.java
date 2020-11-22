package com.rined.proposal.service;

import com.rined.proposal.model.ProposalBriefForConsumer;
import com.rined.proposal.model.dto.ConsumerDto;

import java.util.List;

public interface ProposalConsumerService {

    List<ProposalBriefForConsumer> getBriefProposals(ConsumerDto consumerDto);

}
