package com.rined.deal.mail.services;

import com.rined.deal.mail.conerter.LogConverter;
import com.rined.deal.mail.model.DestinationDto;
import com.rined.deal.mail.model.Log;
import com.rined.deal.mail.repositories.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MongoLogger implements GenericHandler<DestinationDto> {
    private final LogConverter converter;
    private final LogRepository repository;

    @Override
    public Object handle(DestinationDto destinationDto, MessageHeaders messageHeaders) {
        repository.save(converter.convert(destinationDto, messageHeaders));
        return destinationDto;
    }
}
