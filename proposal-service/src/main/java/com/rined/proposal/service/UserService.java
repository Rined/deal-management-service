package com.rined.proposal.service;

import com.rined.proposal.model.dto.ConsumerDto;
import com.rined.proposal.model.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, UserDto> getUsers(ConsumerDto consumerDto, List<String> ids);

    UserDto getUser(ConsumerDto consumerDto, String ids);

}
