package com.rined.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulGatewayService {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayService.class, args);
    }
}
