package com.rined.security;

import com.rined.security.model.Role;
import com.rined.security.model.User;
import com.rined.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties
public class AuthService {
    private final UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(AuthService.class);
    }

    @PostConstruct
    void init(){
        repository.save(new User("provider", "password", Collections.singleton(Role.PROVIDER)));
        repository.save(new User("consumer", "password", Collections.singleton(Role.CONSUMER)));
    }
}
