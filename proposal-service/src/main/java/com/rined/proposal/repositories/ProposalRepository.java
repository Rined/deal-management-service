package com.rined.proposal.repositories;

import com.rined.proposal.model.Proposal;
import com.rined.proposal.model.ProposalBriefForConsumer;
import com.rined.proposal.model.ProposalBriefForProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends MongoRepository<Proposal, String>, ProposalRepositoryCustom {

    boolean existsByIdAndProviderId(String id, String providerId);

    @Query(value = "{}", fields = "{_id : 1, name : 1, price : 1, providerId : 1}")
    List<ProposalBriefForConsumer> getAllBriefProposalsForConsumer();

    @Query(value = "{'providerId': :#{#providerId}}", fields = "{_id : 1, name : 1}")
    List<ProposalBriefForProvider> getAllBriefProposals(@Param("providerId") String providerId);

    Optional<Proposal> findByIdAndProviderId(String id, String providerId);

    void deleteByIdAndProviderId(String id, String providerId);

    List<Proposal> findAllByProviderId(String providerId);

}
