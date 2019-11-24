package com.rined.template.converter;

import com.rined.template.controllers.dto.TemplateRequestDto;
import com.rined.template.controllers.dto.UserDto;
import com.rined.template.model.Template;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class TemplateConverterImpl implements TemplateConverter {
    @Override
    public Template requestDtoToBean(TemplateRequestDto dto, UserDto userDto) {
        Objects.requireNonNull(dto);
        Objects.requireNonNull(userDto);
        return new Template(
                dto.getTemplateName(),
                dto.getFields(),
                dto.getFormat(),
                userDto.getId()
        );
    }
}
