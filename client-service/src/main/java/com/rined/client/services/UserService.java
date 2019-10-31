package com.rined.client.services;

import com.rined.client.dto.response.ResponseUserBrief;
import com.rined.client.model.collections.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    List<ResponseUserBrief> getAllUserBrief();

    User getUserById(String userId);

}
