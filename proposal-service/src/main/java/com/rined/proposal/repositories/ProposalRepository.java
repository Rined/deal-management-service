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

    @Query(value = "{'authorId': :#{#authorId}}", fields = "{_id : 1, name : 1}")
    List<ProposalBrief> getAllBriefProposals(@Param("authorId") String authorId);

    Optional<Proposal> findByIdAndAuthorId(String id, String authorId);

    void deleteByIdAndAuthorId(String id, String authorId);

    List<Proposal> findAllByAuthorId(String authorId);

}
