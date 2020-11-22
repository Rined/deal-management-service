package com.rined.template.services;

import com.rined.template.converter.TemplateConverter;
import com.rined.template.exception.AlreadyExistsException;
import com.rined.template.exception.NotFoundException;
import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;
import com.rined.template.model.dto.ProviderDto;
import com.rined.template.model.dto.TemplateCreateRequestDto;
import com.rined.template.model.dto.TemplateRequestDto;
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
    public List<Template> getAllTemplates(ProviderDto providerDto) {
        return repository.findAllByProviderId(providerDto.getId());
    }

    @Override
    public Template getTemplateById(String templateId, ProviderDto providerDto) {
        return repository.findByIdAndProviderId(templateId, providerDto.getId())
                .orElseThrow(() -> new NotFoundException("Template with id %s not found!", templateId));
    }

    @Override
    public void deleteById(String templateId, ProviderDto providerDto) {
        repository.deleteByIdAndProviderId(templateId, providerDto.getId());
    }

    @Override
    public Template createTemplate(TemplateCreateRequestDto templateDto, ProviderDto providerDto) {
        Template template = converter.requestCreateDtoToBean(templateDto, providerDto);
        if (repository.existsByIdAndProviderId(template.getId(), template.getProviderId())) {
            throw new AlreadyExistsException("Template already exists!");
        }
        return repository.save(template);
    }

    @Override
    public Template updateTemplate(String templateId, TemplateRequestDto templateDto, ProviderDto providerDto) {
        Template template = repository.findByIdAndProviderId(templateId, providerDto.getId())
                .orElseThrow(() -> new NotFoundException("Template with id %s not found!", templateId));
        template.setFields(templateDto.getFields());
        template.setFormat(templateDto.getFormat());
        template.setTemplateName(templateDto.getTemplateName());
        return repository.save(template);
    }

    @Override
    public List<TemplateBrief> getAllBriefTemplates(ProviderDto providerDto) {
        return repository.getAllBriefTemplates(providerDto.getId());
    }
}
