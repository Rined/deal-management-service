package com.rined.deal.mail.conerter;

import com.rined.deal.mail.model.DestinationDto;
import com.rined.deal.mail.model.Log;
import com.rined.deal.mail.model.SenderService;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import static com.rined.deal.mail.config.IntegrationConfig.FROM_SERVICE_HEADER;

@Service
public class LogConverterImpl implements LogConverter {

    @Override
    public Log convert(DestinationDto destinationDto, MessageHeaders messageHeaders) {
        return new Log(
                destinationDto,
                messageHeaders.get(FROM_SERVICE_HEADER, SenderService.class)
        );
    }
}
