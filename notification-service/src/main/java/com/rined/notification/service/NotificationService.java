package com.rined.notification.service;

import com.rined.notification.model.Notification;
import com.rined.notification.model.dto.MessageCountDto;
import com.rined.notification.model.dto.NotificationRequestDto;
import com.rined.notification.model.dto.NotificationResponseDto;
import com.rined.notification.model.dto.ReadMessageDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationService {

    Mono<Notification> save(String userId, NotificationRequestDto notification);

    Flux<NotificationResponseDto> userNotifications(String userId);

    Flux<NotificationResponseDto> unreadUserNotifications(String userId);

    Mono<MessageCountDto> unreadMessageCount(String userId);

    Mono<Void> readMessage(ReadMessageDto readMessageDto);
}
