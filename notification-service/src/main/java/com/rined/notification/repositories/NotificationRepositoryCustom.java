package com.rined.notification.repositories;

import com.rined.notification.model.dto.ReadMessageDto;
import reactor.core.publisher.Mono;

public interface NotificationRepositoryCustom {

    Mono<Void> readMessage(ReadMessageDto readMessageDto);

}
