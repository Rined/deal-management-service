package com.rined.proposal.service;

import com.rined.proposal.feign.UserFeignClient;
import com.rined.proposal.model.dto.ConsumerDto;
import com.rined.proposal.model.dto.UserDto;
import com.rined.proposal.model.dto.UsersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserFeignClient feignClient;

    @Override
    public Map<String, UserDto> getUsers(ConsumerDto consumerDto, List<String> ids) {
        return feignClient.getUsersByIds(
                consumerDto.getId(),
                consumerDto.getUsername(),
                consumerDto.getUsername(),
                new UsersRequest(ids)
        ).stream().collect(Collectors.toMap(UserDto::getId, userDto -> userDto));
    }

    @Override
    public UserDto getUser(ConsumerDto consumerDto, String id) {
        Map<String, UserDto> users = getUsers(consumerDto, Collections.singletonList(id));
        return users.get(id);
    }
}
