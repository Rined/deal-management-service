package com.rined.deal.mail.config;

import com.rined.deal.mail.model.SenderService;
import com.rined.deal.mail.services.DealMailTransformer;
import com.rined.deal.mail.services.MongoLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;

@Configuration
@RequiredArgsConstructor
@IntegrationComponentScan
public class IntegrationConfig {
    public static final String FROM_SERVICE_HEADER = "fromService";
    public static final String CHANNEL_NAME = "serviceReceive";

    private static final int CHANNEL_CAPACITY = 100;
    private static final int DEFAULT_POLLER_PERIOD = 1000;

    private final MongoLogger logger;
    private final DealMailTransformer dealMailTransformer;
    private final JavaMailSender mailSender;

    @Bean(name = CHANNEL_NAME)
    public PollableChannel receiveMsgChannel() {
        return MessageChannels.queue(CHANNEL_NAME, CHANNEL_CAPACITY).get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(DEFAULT_POLLER_PERIOD).get();
    }

    @Bean
    public IntegrationFlow mailSenderFlow() {
        return f -> f
                .channel(receiveMsgChannel())
                .handle(logger)
                .route(Message.class,
                        m -> m.getHeaders().get(FROM_SERVICE_HEADER, SenderService.class),
                        mapping -> mapping
                                .subFlowMapping(SenderService.AUTH, IntegrationFlowDefinition::nullChannel)
                                .subFlowMapping(SenderService.TEMPLATE, IntegrationFlowDefinition::nullChannel)
                                .subFlowMapping(SenderService.PROPOSAL, IntegrationFlowDefinition::nullChannel)
                                .subFlowMapping(SenderService.DEAL,
                                        sub -> sub
                                                .transform(dealMailTransformer, "transform")
                                                .handle(m -> mailSender.send((SimpleMailMessage) m.getPayload()))
                                )
                );
    }

}
