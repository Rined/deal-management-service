package com.rined.security.service;

import com.rined.security.converter.RegistrationConverter;
import com.rined.security.dto.request.RegistrationRequestDto;
import com.rined.security.exception.AlreadyExistsException;
import com.rined.security.model.User;
import com.rined.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final RegistrationConverter converter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with name %s not found!", username)));
    }

    @Override
    public void save(RegistrationRequestDto registrationRequestDto) {
        final String username = registrationRequestDto.getLogin();
        if (repository.existsByUsername(username)) {
            throw new AlreadyExistsException("User with name %s already exists!", username);
        }
        User user = converter.registrationDtoToUser(registrationRequestDto);
        repository.save(user);
    }
}
