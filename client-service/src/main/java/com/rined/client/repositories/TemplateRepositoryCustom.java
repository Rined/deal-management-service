package com.rined.client.repositories;

import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.model.collections.Template;
import com.rined.client.model.collections.Data;

import java.util.List;

public interface TemplateRepositoryCustom {

    List<ResponseTemplateNameDto> userActiveTemplateNameList(String userId);

    List<Template> userActiveTemplateList(String userId);

    List<Data> completedFilledTemplates(String userId);

    List<Data> sentFilledTemplates(String userId);

    Template getActiveTemplateForUser(String userId, String templateId);

}
