package com.rined.auth.controller;

import com.rined.auth.exceptions.AlreadyExistsException;
import com.rined.auth.model.Role;
import com.rined.auth.model.User;
import com.rined.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserRepository repository;

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registration(@RequestBody User userDto) {
        User user = repository.findByUsername(userDto.getUsername());

        if (Objects.nonNull(user)) {
            throw new AlreadyExistsException("User with username %s already exists", userDto.getUsername());
        }

        System.out.println(userDto);

        userDto.setRoles(new HashSet<Role>() {{
            add(Role.PROVIDER);
            add(Role.CONSUMER);
        }});

        repository.save(userDto);
    }

}
