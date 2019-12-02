package com.rined.deal.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("batch")
public class BatchProperties {
    private String directoryPath;
    private int chunk;
}
