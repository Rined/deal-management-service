package com.rined.security.service;


import com.rined.security.model.User;

public interface TokenService {

    String transform(User user);

}
