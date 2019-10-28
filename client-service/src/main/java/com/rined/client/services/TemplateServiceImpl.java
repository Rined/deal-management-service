package com.rined.client.services;

import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.exceptions.NotFoundException;
import com.rined.client.model.DocumentTemplate;
import com.rined.client.model.FilledTemplateData;
import com.rined.client.repositories.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    @Override
    public List<DocumentTemplate> templateList() {
        return templateRepository.findAll();
    }

    @Override
    public DocumentTemplate templateById(String id) {
        return templateRepository.findById(id).orElseThrow(() -> new NotFoundException("Template not found"));
    }

    @Override
    public List<ResponseTemplateNameDto> templateBriefList() {
        return templateRepository.getTemplateIdName();
    }

    @Override
    public List<ResponseTemplateNameDto> userActiveTemplateNameList(String userId) {
        return templateRepository.userActiveTemplateNameList(userId);
    }

    @Override
    public List<DocumentTemplate> userActiveTemplateList(String userId) {
        return templateRepository.userActiveTemplateList(userId);
    }

    @Override
    public List<FilledTemplateData> userSentTemplateList(String userId) {
        return templateRepository.sentFilledTemplates(userId);
    }

    @Override
    public List<FilledTemplateData> userCompletedTemplateList(String userId) {
        return templateRepository.completedFilledTemplates(userId);
    }
}
