package com.rined.deal.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
@ConfigurationProperties("spring.data.mongodb")
public class MongoConfigurationProperties {
    private int port;
    private String database;
}
