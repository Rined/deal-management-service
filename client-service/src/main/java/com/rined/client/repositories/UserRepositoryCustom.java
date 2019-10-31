package com.rined.client.repositories;

import com.rined.client.dto.response.ResponseUserBrief;

import java.util.List;

public interface UserRepositoryCustom {

    List<ResponseUserBrief> getBriefUsers();
}
