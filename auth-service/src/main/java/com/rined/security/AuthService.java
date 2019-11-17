package com.rined.security;

import com.rined.security.model.Role;
import com.rined.security.model.User;
import com.rined.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(AuthService.class);
    }

    @PostConstruct
    void init(){
        repository.save(new User("user", "password", Collections.singleton(Role.USER)));
        repository.save(new User("admin", "password", Collections.singleton(Role.ADMIN)));
    }
}
