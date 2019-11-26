package com.rined.template.converter;

import com.rined.template.model.dto.TemplateRequestDto;
import com.rined.template.model.dto.ProviderDto;
import com.rined.template.model.Template;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class TemplateConverterImpl implements TemplateConverter {
    @Override
    public Template requestDtoToBean(TemplateRequestDto dto, ProviderDto providerDto) {
        Objects.requireNonNull(dto);
        Objects.requireNonNull(providerDto);
        return new Template(
                dto.getTemplateName(),
                dto.getFields(),
                dto.getFormat(),
                providerDto.getId()
        );
    }
}
