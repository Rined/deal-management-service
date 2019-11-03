package com.rined.template.services;


import com.rined.template.controllers.dto.TemplateRequestDto;
import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;

import java.util.List;

public interface TemplateService {

    List<Template> getAllTemplates();

    Template getTemplateById(String templateId);

    void deleteById(String templateId);

    void createTemplate(TemplateRequestDto templateDto);

    void updateTemplate(String templateId, TemplateRequestDto templateDto);

    List<TemplateBrief> getAllBriefTemplates();
}
