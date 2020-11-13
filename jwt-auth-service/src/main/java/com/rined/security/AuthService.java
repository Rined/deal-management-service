package com.rined.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AuthService {
    public static void main(String[] args) {
        SpringApplication.run(AuthService.class);
    }
}
