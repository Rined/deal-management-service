package com.rined.client.services;

import com.rined.client.dto.response.ResponseDataBrief;
import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.exceptions.NotFoundException;
import com.rined.client.model.collections.Template;
import com.rined.client.model.collections.Data;
import com.rined.client.repositories.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    @Override
    public List<Template> templateList() {
        return templateRepository.findAll();
    }

    @Override
    public Template templateById(String id) {
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
    public List<Template> userActiveTemplateList(String userId) {
        return templateRepository.userActiveTemplateList(userId);
    }

    @Override
    public List<Data> userSentTemplateList(String userId) {
        return templateRepository.sentFilledTemplates(userId);
    }

    @Override
    public List<Data> userCompletedTemplateList(String userId) {
        return templateRepository.completedFilledTemplates(userId);
    }

    @Override
    public Template activeTemplateForUserById(String userId, String templateId) {
        return templateRepository.getActiveTemplateForUser(userId, templateId);
    }

    @Override
    public Data sentTemplateForUserById(String userId, String templateId) {
        return null;
    }

    @Override
    public List<ResponseDataBrief> userSentTemplateBriefList(String userId) {
        return null;
    }

    @Override
    public List<ResponseDataBrief> userCompletedTemplateBriefList(String userId) {
        return null;
    }

    @Override
    public Data completedTemplateListForUserById(String userId, String templateId) {
        return null;
    }
}
