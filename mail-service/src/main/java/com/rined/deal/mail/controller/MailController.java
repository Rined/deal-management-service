package com.rined.deal.mail.controller;

import com.rined.deal.mail.integration.MailGateway;
import com.rined.deal.mail.model.DestinationDto;
import com.rined.deal.mail.model.SenderService;
import com.rined.deal.mail.resolver.Sender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MailController {
    private final MailGateway gateway;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/mail/send")
    public void consumerAcceptDeal(@Sender SenderService sender,
                                   @RequestBody DestinationDto destination) {
        gateway.processMessage(destination, sender);
    }
}
