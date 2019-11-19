package com.rined.security.controller;

import com.rined.security.dto.RegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public void registration(@Valid @RequestBody RegistrationDto registrationDto){

    }

}
