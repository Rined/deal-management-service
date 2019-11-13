package com.rined.proposal.repositories;

import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProposalRepository extends MongoRepository<Proposal, String> {

    @Query(value = "{}", fields = "{_id : 1, name : 1}")
    List<ProposalBrief> getAllBriefProposals();

}
