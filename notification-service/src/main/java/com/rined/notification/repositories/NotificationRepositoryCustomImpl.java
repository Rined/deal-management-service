package com.rined.notification.repositories;

import com.rined.notification.model.Notification;
import com.rined.notification.model.dto.ReadMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom {
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Void> readMessage(ReadMessageDto readMessageDto) {
        Query query = query(where("id").is(readMessageDto.getMessageId()));
        Update update = new Update();
        update.set("unread", false);
        return mongoTemplate.updateMulti(
                query,
                update,
                Notification.class)
                .then();
    }
}
