package com.rined.client.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ResponseUserBrief {

    private String id;

    private String name;

    private String company;
}
