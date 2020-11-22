package com.rined.crud.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonProperty("id")
    @JsonView(Views.GetDto.class)
    private String id;

    @JsonProperty("firstName")
    @JsonView(Views.General.class)
    private String firstName;

    @JsonProperty("lastName")
    @JsonView(Views.General.class)
    private String lastName;

    @JsonProperty("city")
    @JsonView(Views.General.class)
    private String city;

    @JsonProperty("company")
    @JsonView(Views.General.class)
    private String company;

    @JsonProperty("about")
    @JsonView(Views.General.class)
    private String about;

    public UserDto(String userId) {
        this.id = userId;
    }
}
