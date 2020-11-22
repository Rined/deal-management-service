package com.rined.template.services;


import com.rined.template.model.dto.TemplateCreateRequestDto;
import com.rined.template.model.dto.TemplateRequestDto;
import com.rined.template.model.dto.ProviderDto;
import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;

import java.util.List;

public interface TemplateService {

    List<Template> getAllTemplates(ProviderDto providerDto);

    Template getTemplateById(String templateId, ProviderDto providerDto);

    void deleteById(String templateId, ProviderDto providerDto);

    Template createTemplate(TemplateCreateRequestDto templateDto, ProviderDto providerDto);

    Template updateTemplate(String templateId, TemplateRequestDto templateDto, ProviderDto providerDto);

    List<TemplateBrief> getAllBriefTemplates(ProviderDto providerDto);
}
