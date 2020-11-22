package com.rined.crud.repository;

import com.rined.crud.model.User;
import com.rined.crud.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findUsersByIdIn(List<String> ids);

}
