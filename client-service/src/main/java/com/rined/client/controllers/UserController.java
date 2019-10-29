package com.rined.client.controllers;

import com.rined.client.model.collections.User;
import com.rined.client.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //todo
    @GetMapping("/users/brief")
    public List<User> getAllBriefUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    //todo
    @GetMapping("/users/{userId}/info")
    public User getUserInfo(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }


}
