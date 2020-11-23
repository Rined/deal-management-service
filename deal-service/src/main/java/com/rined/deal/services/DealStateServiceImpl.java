package com.rined.deal.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rined.deal.converter.DealConverter;
import com.rined.deal.exception.InvalidStateChange;
import com.rined.deal.exception.NotFoundException;
import com.rined.deal.exception.ValidationException;
import com.rined.deal.model.Deal;
import com.rined.deal.model.DealInfo;
import com.rined.deal.model.DealState;
import com.rined.deal.model.FieldInfo;
import com.rined.deal.model.dto.ConsumerDto;
import com.rined.deal.model.dto.DealConsumerRequestInfoDto;
import com.rined.deal.model.dto.DealRequestInfoDto;
import com.rined.deal.model.dto.ProviderDto;
import com.rined.deal.repositories.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rined.deal.model.DealState.*;

@Service
@RequiredArgsConstructor
public class DealStateServiceImpl implements DealStateService {
    private final DealRepository repository;
    private final DealConverter converter;
    private final StateChangeChecker checker;
    private final DealTransactionalOutbox transactionalOutbox;

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Deal declineConsumer(String dealId, ConsumerDto consumer) {
        return changeConsumerState(dealId, consumer.getId(), CONSUMER_DECLINE);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Deal acceptConsumer(String dealId, ConsumerDto consumerDto) {
        return changeConsumerState(dealId, consumerDto.getId(), PROVIDER_REQUEST_INFO);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Deal requestInfoProvider(String dealId, ProviderDto providerDto, DealRequestInfoDto dealRequestInfoDto) {
        Deal deal = repository.findByIdAndProviderId(dealId, providerDto.getId())
                .orElseThrow(() -> new NotFoundException("Deal with id % not found", dealId));
        if (checker.isInvalidStateChange(deal.getState(), CONSUMER_PROVIDE_INFO)) {
            throw new InvalidStateChange("Invalid state change");
        }
        List<FieldInfo> info = converter.convertProviderRequestInfo(dealRequestInfoDto);
        DealInfo dealInfo = deal.getDealInfo();
        dealInfo.setInfo(info);
        deal.setState((dealRequestInfoDto.getFields() == null || dealRequestInfoDto.getFields().size() == 0)
                ? PAYMENT
                : CONSUMER_PROVIDE_INFO);
        return repository.save(deal);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Deal requestInfoConsumer(String dealId, ConsumerDto consumerDto, DealConsumerRequestInfoDto requestInfo) {
        Deal deal = repository.findByIdAndConsumerId(dealId, consumerDto.getId())
                .orElseThrow(() -> new NotFoundException("Deal with id % not found", dealId));
        if (checker.isInvalidStateChange(deal.getState(), PAYMENT)) {
            throw new InvalidStateChange("Invalid state change");
        }
        List<FieldInfo> info = converter.convertConsumerRequestInfo(requestInfo);
        DealInfo dealInfo = deal.getDealInfo();
        if (!validateDealInfo(dealInfo.getInfo(), info)) {
            throw new ValidationException("Bad request for deal %s", dealId);
        }
        dealInfo.setInfo(info);
        deal.setState(PAYMENT);
        return repository.save(deal);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Deal declineProvider(String dealId, ProviderDto provider) {
        return changeProviderState(dealId, provider.getId(), PROVIDER_DECLINE);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Deal acceptProvider(String dealId, ProviderDto providerDto) {
        return changeProviderState(dealId, providerDto.getId(), CONSUMER_ACCEPT);
    }

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Deal doneProvider(String dealId, ProviderDto providerDto) {
        return changeProviderState(dealId, providerDto.getId(), DONE);
    }

    @Override
    public Deal payDeal(String dealId, ConsumerDto consumerDto) {
        return transactionalOutbox.persistDeal(dealId, consumerDto.getId(), PAYMENT_VERIFICATION);
    }

    private boolean validateDealInfo(List<FieldInfo> prevInfo, List<FieldInfo> newInfo) {
        for (FieldInfo fieldInfo : prevInfo) {
            String uuid = fieldInfo.getUuid();
            String description = fieldInfo.getDescription();
            long count = newInfo.stream()
                    .filter(fi -> fi.getUuid().equals(uuid))
                    .filter(fi -> fi.getDescription().equals(description))
                    .count();
            if (count != 1)
                return false;
        }
        return true;
    }

    private Deal changeConsumerState(String dealId, String consumerId, DealState newState) {
        Deal deal = repository.findByIdAndConsumerId(dealId, consumerId)
                .orElseThrow(() -> new NotFoundException("Deal with id % not found", dealId));
        if (checker.isInvalidStateChange(deal.getState(), newState)) {
            throw new InvalidStateChange("Invalid state change");
        }
        deal.setState(newState);
        return repository.save(deal);
    }

    private Deal changeProviderState(String dealId, String providerId, DealState newState) {
        Deal deal = repository.findByIdAndProviderId(dealId, providerId)
                .orElseThrow(() -> new NotFoundException("Deal with id % not found", dealId));
        if (checker.isInvalidStateChange(deal.getState(), newState)) {
            throw new InvalidStateChange("Invalid state change");
        }
        deal.setState(newState);
        return repository.save(deal);
    }
}
