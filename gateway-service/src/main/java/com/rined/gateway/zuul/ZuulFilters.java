package com.rined.gateway.zuul;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilters {

    @Bean
    public HeaderConverterFilter headerFilter() {
        return new HeaderConverterFilter();
    }

}
