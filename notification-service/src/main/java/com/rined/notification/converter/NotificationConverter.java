package com.rined.notification.converter;

import com.rined.notification.model.Notification;
import com.rined.notification.model.dto.MessageCountDto;
import com.rined.notification.model.dto.NotificationRequestDto;
import com.rined.notification.model.dto.NotificationResponseDto;

public interface NotificationConverter {

    Notification dtoToNotification(NotificationRequestDto dto, String userId);

    NotificationResponseDto notificationToResponseDto(Notification notification);

    MessageCountDto countToDto(long count);
}
