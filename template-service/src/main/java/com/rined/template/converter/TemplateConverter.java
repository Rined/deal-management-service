package com.rined.template.converter;

import com.rined.template.model.dto.TemplateCreateRequestDto;
import com.rined.template.model.dto.TemplateRequestDto;
import com.rined.template.model.dto.ProviderDto;
import com.rined.template.model.Template;

public interface TemplateConverter {

    Template requestCreateDtoToBean(TemplateCreateRequestDto dto, ProviderDto providerDto);

}
