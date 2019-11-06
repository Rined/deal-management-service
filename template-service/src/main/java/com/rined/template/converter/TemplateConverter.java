package com.rined.template.converter;

import com.rined.template.controllers.dto.TemplateRequestDto;
import com.rined.template.model.Template;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
@SuppressWarnings("UnmappedTargetProperties")
public interface TemplateConverter {

    @Mappings({
            @Mapping(target = "templateName", source = "dto.templateName"),
            @Mapping(target = "format", source = "dto.format"),
            @Mapping(target = "fields", source = "dto.fields")
    })
    Template requestDtoToBean(TemplateRequestDto dto);

}
