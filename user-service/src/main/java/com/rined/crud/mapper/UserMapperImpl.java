package com.rined.crud.mapper;

import com.rined.crud.model.AuthenticatedUser;
import com.rined.crud.model.User;
import com.rined.crud.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto convertUserToGetDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCity(),
                user.getCompany(),
                user.getAbout()
        );
    }

    @Override
    public UserDto convertAuthenticatedUserToDto(AuthenticatedUser authenticatedUser) {
        return new UserDto(authenticatedUser.getUserId());
    }

    @Override
    public User convertDtoAndUserToUser(User dbUser, UserDto putUserDto) {
        return new User(
                dbUser.getId(),
                putUserDto.getFirstName(),
                putUserDto.getLastName(),
                putUserDto.getCity(),
                putUserDto.getCompany(),
                putUserDto.getAbout()
        );
    }

    @Override
    public User convertDtoAndAuthenticatedUserToUser(AuthenticatedUser authenticatedUser, UserDto putUserDto) {
        return new User(
                authenticatedUser.getUserId(),
                putUserDto.getFirstName(),
                putUserDto.getLastName(),
                putUserDto.getCity(),
                putUserDto.getCompany(),
                putUserDto.getAbout()
        );
    }
}
