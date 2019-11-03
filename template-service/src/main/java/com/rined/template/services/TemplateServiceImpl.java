package com.rined.template.services;

import com.rined.template.controllers.dto.TemplateRequestDto;
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
    public List<Template> getAllTemplates() {
        return repository.findAll();
    }

    @Override
    public Template getTemplateById(String templateId) {
        return repository.findById(templateId)
                .orElseThrow(() -> new NotFoundException("Template with id %s not found!", templateId));
    }

    @Override
    public void deleteById(String templateId) {
        repository.deleteById(templateId);
    }

    @Override
    public void createTemplate(TemplateRequestDto templateDto) {
        Template template = converter.requestDtoToBean(templateDto);
        repository.save(template);
    }

    @Override
    public void updateTemplate(String templateId, TemplateRequestDto templateDto) {
        Template template = converter.requestDtoToBean(templateDto);
        template.setId(templateId);
        repository.save(template);
    }

    @Override
    public List<TemplateBrief> getAllBriefTemplates() {
        return repository.getAllBriefTemplates();
    }
}
