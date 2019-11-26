package com.rined.template.services;

import com.rined.template.model.dto.TemplateRequestDto;
import com.rined.template.model.dto.UserDto;
import com.rined.template.converter.TemplateConverter;
import com.rined.template.exception.NotFoundException;
import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;
import com.rined.template.repositories.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository repository;
    private final TemplateConverter converter;

    @Override
    public List<Template> getAllTemplates(UserDto userDto) {
        return repository.findAllByAuthorId(userDto.getId());
    }

    @Override
    public Template getTemplateById(String templateId, UserDto userDto) {
        return repository.findByIdAndAuthorId(templateId, userDto.getId())
                .orElseThrow(() -> new NotFoundException("Template with id %s not found!", templateId));
    }

    @Override
    public void deleteById(String templateId, UserDto userDto) {
        repository.deleteByIdAndAuthorId(templateId, userDto.getId());
    }

    @Override
    public void createTemplate(TemplateRequestDto templateDto, UserDto userDto) {
        Template template = converter.requestDtoToBean(templateDto, userDto);
        repository.save(template);
    }

    @Override
    public void updateTemplate(String templateId, TemplateRequestDto templateDto, UserDto userDto) {
        Template template = repository.findByIdAndAuthorId(templateId, userDto.getId())
                .orElseThrow(() -> new NotFoundException("Proposal with id %s not found!", templateId));
        template.setFields(templateDto.getFields());
        template.setFormat(templateDto.getFormat());
        template.setTemplateName(templateDto.getTemplateName());
        repository.save(template);
    }

    @Override
    public List<TemplateBrief> getAllBriefTemplates(UserDto userDto) {
        return repository.getAllBriefTemplates(userDto.getId());
    }
}
