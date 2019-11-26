package com.rined.template.converter;

import com.rined.template.model.dto.TemplateRequestDto;
import com.rined.template.model.dto.UserDto;
import com.rined.template.model.Template;

public interface TemplateConverter {

    Template requestDtoToBean(TemplateRequestDto dto, UserDto userDto);

}
