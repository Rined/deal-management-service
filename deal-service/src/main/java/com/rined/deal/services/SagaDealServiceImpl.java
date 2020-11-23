package com.rined.deal.services;

import com.rined.deal.converter.DealConverter;
import com.rined.deal.model.AbortedDeal;
import com.rined.deal.model.Deal;
import com.rined.deal.model.dto.SagaDealRequest;
import com.rined.deal.repositories.AbortedDealRepository;
import com.rined.deal.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SagaDealServiceImpl implements SagaDealService {
    private final DealRepository dealRepository;
    private final AbortedDealRepository abortedDealRepository;
    private final DealConverter dealConverter;

    @Override
    @Transactional
    public void sagaApprove(SagaDealRequest request) {
        List<Deal> dealsByProposal = dealRepository.findAllByDealInfo_ProposalId(request.getProposalId());
        List<AbortedDeal> abortedDeals = dealConverter.convertDealToAborted(dealsByProposal, request.getDealId());
        abortedDealRepository.saveAll(abortedDeals);
        dealRepository.removeAllByDealInfo_ProposalIdAndIdNot(request.getProposalId(), request.getDealId());
    }

    @Override
    @Transactional
    public void sagaReject(SagaDealRequest request) {
        List<AbortedDeal> abortedDealsByProposal
                = abortedDealRepository.findAllByDealInfo_ProposalId(request.getProposalId());
        List<Deal> deals = dealConverter.convertAbortedToDeal(abortedDealsByProposal);
        dealRepository.saveAll(deals);
        abortedDealRepository.removeAllByDealInfo_ProposalId(request.getProposalId());
    }
}
