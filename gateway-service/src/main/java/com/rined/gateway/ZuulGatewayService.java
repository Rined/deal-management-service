package com.rined.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableConfigurationProperties
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) //disable password generated message
public class ZuulGatewayService {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayService.class, args);
    }
}
