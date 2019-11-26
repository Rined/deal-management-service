package com.rined.proposal.repositories;

import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBrief;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends MongoRepository<Proposal, String>, ProposalRepositoryCustom {

    @Query(value = "{}", fields = "{_id : 1, name : 1}")
    List<ProposalBrief> getAllBriefProposalsForConsumer();

    @Query(value = "{'providerId': :#{#providerId}}", fields = "{_id : 1, name : 1}")
    List<ProposalBrief> getAllBriefProposals(@Param("providerId") String providerId);

    Optional<Proposal> findByIdAndProviderId(String id, String providerId);

    void deleteByIdAndProviderId(String id, String providerId);

    List<Proposal> findAllByProviderId(String providerId);

}
