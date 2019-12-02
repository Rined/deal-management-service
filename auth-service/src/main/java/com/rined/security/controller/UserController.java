package com.rined.security.controller;

import com.rined.security.dto.request.RegistrationRequestDto;
import com.rined.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/registration")
    public void registration(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
        service.save(registrationRequestDto);
    }
}
