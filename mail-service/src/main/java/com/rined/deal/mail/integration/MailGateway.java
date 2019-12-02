package com.rined.deal.mail.integration;

import com.rined.deal.mail.model.SenderService;
import com.rined.deal.mail.model.DestinationDto;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import static com.rined.deal.mail.config.IntegrationConfig.CHANNEL_NAME;
import static com.rined.deal.mail.config.IntegrationConfig.FROM_SERVICE_HEADER;

@MessagingGateway
public interface MailGateway {

    @Gateway(requestChannel = CHANNEL_NAME)
    void processMessage(@Payload DestinationDto destinationDto,
                        @Header(name = FROM_SERVICE_HEADER) SenderService senderService);

}
