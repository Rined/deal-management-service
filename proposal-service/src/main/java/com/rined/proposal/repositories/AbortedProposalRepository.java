package com.rined.proposal.repositories;

import com.rined.proposal.model.AbortedProposal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbortedProposalRepository extends MongoRepository<AbortedProposal, String> {

}
