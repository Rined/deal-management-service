package com.rined.deal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableCircuitBreaker
@SpringBootApplication
@EnableMongoRepositories
public class DealServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(DealServiceMain.class);
    }
}
