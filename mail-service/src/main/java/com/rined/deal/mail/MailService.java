package com.rined.deal.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MailService {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MailService.class);
//        MailGateway gateway = run.getBean(MailGateway.class);
//        gateway.processMessage(new DestinationDto("Rined", "login", "testmailforotus@mail.ru"), SenderService.AUTH);
//        gateway.processMessage(new DestinationDto("Rined", "create", "testmailforotus@mail.ru"), SenderService.TEMPLATE);
//        gateway.processMessage(new DestinationDto("Rined", "create", "testmailforotus@mail.ru"), SenderService.PROPOSAL);
//        gateway.processMessage(new DestinationDto("Rined", "start", "testmailforotus@mail.ru"), SenderService.DEAL);
    }
}
