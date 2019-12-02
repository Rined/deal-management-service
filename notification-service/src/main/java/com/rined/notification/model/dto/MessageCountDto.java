package com.rined.notification.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageCountDto {

    @JsonProperty("count")
    private long count;

}
