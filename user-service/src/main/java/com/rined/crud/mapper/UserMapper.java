package com.rined.crud.mapper;


import com.rined.crud.model.AuthenticatedUser;
import com.rined.crud.model.User;
import com.rined.crud.model.dto.UserDto;

public interface UserMapper {

    UserDto convertUserToGetDto(User user);

    UserDto convertAuthenticatedUserToDto(AuthenticatedUser authenticatedUser);

    User convertDtoAndUserToUser(User dbUser, UserDto putUserDto);

    User convertDtoAndAuthenticatedUserToUser(AuthenticatedUser authenticatedUser, UserDto putUserDto);

}
