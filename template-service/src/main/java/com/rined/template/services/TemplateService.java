package com.rined.template.services;


import com.rined.template.controllers.dto.TemplateRequestDto;
import com.rined.template.controllers.dto.UserDto;
import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;

import java.util.List;

public interface TemplateService {

    List<Template> getAllTemplates(UserDto userDto);

    Template getTemplateById(String templateId, UserDto userDto);

    void deleteById(String templateId, UserDto userDto);

    void createTemplate(TemplateRequestDto templateDto, UserDto userDto);

    void updateTemplate(String templateId, TemplateRequestDto templateDto, UserDto userDto);

    List<TemplateBrief> getAllBriefTemplates(UserDto userDto);
}
