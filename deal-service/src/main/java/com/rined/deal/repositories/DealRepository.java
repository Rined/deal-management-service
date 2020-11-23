package com.rined.deal.repositories;

import com.rined.deal.model.Deal;
import com.rined.deal.repositories.projection.DealWrap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DealRepository extends MongoRepository<Deal, String>, DealRepositoryCustom {

    List<Deal> findAllByDealInfo_ProposalId(String proposalId);

    void removeAllByDealInfo_ProposalIdAndIdNot(String proposalId, String id);

    Optional<Deal> findByIdAndConsumerId(String id, String consumerId);

    Optional<Deal> findByIdAndProviderId(String id, String consumerId);

    boolean existsByIdAndConsumerIdAndProviderId(String id, String consumerId, String providerId);

    boolean existsByIdAndConsumerId(String id, String consumerId);

    boolean existsByIdAndProviderId(String id, String providerId);

    @Query(value = "{'id': :#{#id}}", fields = "{state : 1}")
    DealWrap getDealState(@Param("id") String dealId);

}
