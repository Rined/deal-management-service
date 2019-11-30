package com.rined.deal.config;

import com.rined.deal.resolver.ConsumerArgumentResolver;
import com.rined.deal.resolver.ProviderArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ProviderArgumentResolver());
        resolvers.add(new ConsumerArgumentResolver());
    }
}
