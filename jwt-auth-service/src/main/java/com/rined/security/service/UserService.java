package com.rined.security.service;

import com.rined.security.dto.request.RegistrationRequestDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void createUser(RegistrationRequestDto registrationRequestDto);
}
