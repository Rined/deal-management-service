package com.rined.template.converter;

import com.rined.template.controllers.dto.TemplateRequestDto;
import com.rined.template.model.Template;

public interface TemplateConverter {

    Template requestDtoToBean(TemplateRequestDto dto);

}
