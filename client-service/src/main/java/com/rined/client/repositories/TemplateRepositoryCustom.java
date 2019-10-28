package com.rined.client.repositories;

import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.model.DocumentTemplate;
import com.rined.client.model.FilledTemplateData;

import java.util.List;

public interface TemplateRepositoryCustom {

    List<ResponseTemplateNameDto> userActiveTemplateNameList(String userId);

    List<DocumentTemplate> userActiveTemplateList(String userId);

    List<FilledTemplateData> completedFilledTemplates(String userId);

    List<FilledTemplateData> sentFilledTemplates(String userId);
}
