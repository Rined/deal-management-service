package com.rined.template.converter;

import com.rined.template.controllers.dto.TemplateRequestDto;
import com.rined.template.model.Formatter;
import com.rined.template.model.Template;
import com.rined.template.repositories.FormatterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TemplateConverterImpl implements TemplateConverter {

    private final FormatterRepository repository;

    @Override
    public Template requestDtoToBean(TemplateRequestDto dto) {
        List<Formatter> allById = repository.findAllById(dto.getDataTemplates());
        return new Template(dto.getTemplateName(), dto.getFields(), allById);
    }
}
