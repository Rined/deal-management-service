package com.rined.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TemplateService {
    public static void main(String[] args) {
        SpringApplication.run(TemplateService.class);
    }
}
