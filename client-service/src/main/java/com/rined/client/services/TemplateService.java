package com.rined.client.services;

import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.model.DocumentTemplate;
import com.rined.client.model.FilledTemplateData;

import java.util.List;

public interface TemplateService {

    List<DocumentTemplate> templateList();

    DocumentTemplate templateById(String id);

    List<ResponseTemplateNameDto> templateBriefList();

    List<ResponseTemplateNameDto> userActiveTemplateNameList(String userId);

    List<DocumentTemplate> userActiveTemplateList(String userId);

    List<FilledTemplateData> userSentTemplateList(String userId);

    List<FilledTemplateData> userCompletedTemplateList(String userId);
}
