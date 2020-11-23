package com.rined.deal.repositories;

import com.rined.deal.model.AbortedDeal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AbortedDealRepository extends MongoRepository<AbortedDeal, String> {

    List<AbortedDeal> findAllByDealInfo_ProposalId(String proposalId);

    void removeAllByDealInfo_ProposalId(String proposalId);


}
