package com.rined.deal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class DealServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(DealServiceMain.class);
    }
}
