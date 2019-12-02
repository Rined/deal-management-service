package com.rined.notification.repositories;

import com.rined.notification.model.Notification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationRepository extends ReactiveMongoRepository<Notification, String>,
        NotificationRepositoryCustom {

    Flux<Notification> findByUserId(String userId);

    Flux<Notification> findByUserIdAndUnread(String userId, boolean unread);

    Mono<Long> countNotificationByUserIdAndUnread(String userId, boolean unread);

}
