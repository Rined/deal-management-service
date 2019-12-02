package com.rined.notification.service;

import com.rined.notification.converter.NotificationConverter;
import com.rined.notification.model.Notification;
import com.rined.notification.model.dto.MessageCountDto;
import com.rined.notification.model.dto.NotificationRequestDto;
import com.rined.notification.model.dto.NotificationResponseDto;
import com.rined.notification.model.dto.ReadMessageDto;
import com.rined.notification.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository repository;
    private final NotificationConverter converter;

    @Override
    public Flux<NotificationResponseDto> userNotifications(String userId) {
        return repository
                .findByUserId(userId)
                .map(converter::notificationToResponseDto);
    }

    @Override
    public Flux<NotificationResponseDto> unreadUserNotifications(String userId) {
        return repository
                .findByUserIdAndUnread(userId, true)
                .map(converter::notificationToResponseDto);
    }

    @Override
    public Mono<MessageCountDto> unreadMessageCount(String userId) {
        return repository
                .countNotificationByUserIdAndUnread(userId, true)
                .map(converter::countToDto);
    }

    @Override
    public Mono<Void> readMessage(ReadMessageDto readMessageDto) {
        return repository.readMessage(readMessageDto);
    }

    @Override
    public Mono<Notification> save(String userId, NotificationRequestDto dto) {
        return repository
                .save(converter.dtoToNotification(dto, userId));
    }
}
