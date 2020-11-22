package com.rined.crud.service;

import com.rined.crud.model.AuthenticatedUser;
import com.rined.crud.model.dto.UserDto;
import com.rined.crud.model.dto.UsersRequest;

import java.util.List;

public interface UserService {

    UserDto getUserById(AuthenticatedUser user);

    UserDto updateUser(AuthenticatedUser user, UserDto putUserDto);

    List<UserDto> getUsers(UsersRequest request);

}
