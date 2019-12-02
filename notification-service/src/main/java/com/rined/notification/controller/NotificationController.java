package com.rined.notification.controller;

import com.rined.notification.model.Notification;
import com.rined.notification.model.dto.MessageCountDto;
import com.rined.notification.model.dto.NotificationRequestDto;
import com.rined.notification.model.dto.NotificationResponseDto;
import com.rined.notification.model.dto.ReadMessageDto;
import com.rined.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService service;

    @PostMapping("/notification/{userId}")
    public Mono<Notification> receiveNotification(@PathVariable("userId") String userId,
                                                  @RequestBody NotificationRequestDto notification) {
        return service.save(userId, notification);
    }

    @GetMapping("/notification/{userId}")
    public Flux<NotificationResponseDto> getNotification(@PathVariable("userId") String userId) {
        return service.userNotifications(userId);
    }

    @GetMapping("/notification/{userId}/new")
    public Flux<NotificationResponseDto> getUnreadNotification(@PathVariable("userId") String userId) {
        return service.unreadUserNotifications(userId);
    }

    @GetMapping("/notification/{userId}/unread")
    public Mono<MessageCountDto> getUnreadMessageCount(@PathVariable("userId") String userId) {
        return service.unreadMessageCount(userId);
    }

    @PutMapping("/notification")
    public Mono<Void> read(@RequestBody ReadMessageDto readMessageDto){
        return service.readMessage(readMessageDto);
    }
}
