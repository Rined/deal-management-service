package com.rined.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Field("email")
    private String email;

    @Field("firstName")
    private String firstName;

    @Field("secondName")
    private String secondName;

    @Field("birthDate")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @Field("married")
    private boolean married;

}
