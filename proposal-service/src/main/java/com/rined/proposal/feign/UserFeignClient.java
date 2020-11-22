package com.rined.proposal.feign;

import com.rined.proposal.model.dto.UserDto;
import com.rined.proposal.model.dto.UsersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/api/v1")
@FeignClient(name = "user-client", url = "${x.user.service.url}")
public interface UserFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    List<UserDto> getUsersByIds(@RequestHeader("X-UserId") String userId,
                                @RequestHeader("X-Username") String username,
                                @RequestHeader("X-UserEmail") String userEmail,
                                @RequestBody UsersRequest userIds);

}
