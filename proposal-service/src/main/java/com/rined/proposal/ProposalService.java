package com.rined.proposal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@EnableFeignClients
@SpringBootApplication
@PropertySource("classpath:app.properties")
public class ProposalService {
    public static void main(String[] args) {
        SpringApplication.run(ProposalService.class);
    }
}
