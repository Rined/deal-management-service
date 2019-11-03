package com.rined.template.converter;

import com.rined.template.controllers.dto.FormatterRequestDto;
import com.rined.template.model.Formatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
@SuppressWarnings("UnmappedTargetProperties")
public interface FormatterConverter {

    @Mappings({
            @Mapping(target = "name", source = "dto.name"),
            @Mapping(target = "value", source = "dto.value"),
            @Mapping(target = "type", source = "dto.type")
    })
    Formatter convertRequestDtoToBean(FormatterRequestDto dto);

}
