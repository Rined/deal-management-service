package com.rined.notification.converter;

import com.rined.notification.model.Notification;
import com.rined.notification.model.dto.MessageCountDto;
import com.rined.notification.model.dto.NotificationRequestDto;
import com.rined.notification.model.dto.NotificationResponseDto;
import org.springframework.stereotype.Service;

@Service
public class NotificationConverterImpl implements NotificationConverter {

    @Override
    public Notification dtoToNotification(NotificationRequestDto dto, String userId) {
        return new Notification(userId, dto.getMessage());
    }

    @Override
    public NotificationResponseDto notificationToResponseDto(Notification notification) {
        return new NotificationResponseDto(
                notification.getId(),
                notification.getMessage(),
                notification.isUnread()
        );
    }

    @Override
    public MessageCountDto countToDto(long count) {
        return new MessageCountDto(count);
    }
}
