package com.rined.security.converter;

import com.rined.security.dto.request.RegistrationRequestDto;
import com.rined.security.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@SuppressWarnings("UnmappedTargetProperties")
@Mapper(componentModel = "spring")
public interface RegistrationConverter {

    @Mappings({
            @Mapping(target = "username", source = "dto.login"),
            @Mapping(target = "password", source = "dto.password"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "roles", source = "dto.roles"),
    })
    User registrationDtoToUser(RegistrationRequestDto dto);

}
