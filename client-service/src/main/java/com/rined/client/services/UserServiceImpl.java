package com.rined.client.services;

import com.rined.client.exceptions.NotFoundException;
import com.rined.client.model.collections.User;
import com.rined.client.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return repository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User with id %s not found", userId));
    }
}
