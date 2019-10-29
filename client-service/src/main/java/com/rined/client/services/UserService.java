package com.rined.client.services;

import com.rined.client.model.collections.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(String userId);

}
