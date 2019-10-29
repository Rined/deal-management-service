package com.rined.client.model.collections;

import com.rined.client.model.Documents;
import com.rined.client.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Field("username")
    private String username;

    @Field("info")
    private UserInfo userInfo;

    @Field("documents")
    private Documents documents;

    public User(String username, UserInfo userInfo, Documents documents) {
        this.username = username;
        this.userInfo = userInfo;
        this.documents = documents;
    }
}
