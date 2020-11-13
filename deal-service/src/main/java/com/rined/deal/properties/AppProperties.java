package com.rined.deal.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:app.properties")
@ConfigurationProperties("fallback")
public class AppProperties {
    private String persistPath;
}
