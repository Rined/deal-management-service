package com.rined.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("notification")
public class Notification {
    @Id
    private String id;

    @Field("userId")
    private String userId;

    @Field("message")
    private String message;

    @Field("unread")
    private boolean unread;

    public Notification(String userId, String message) {
        this.userId = userId;
        this.message = message;
        this.unread = true;
    }
}
