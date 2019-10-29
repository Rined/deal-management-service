package com.rined.client.services;

import com.rined.client.dto.response.ResponseDataBrief;
import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.model.collections.Template;
import com.rined.client.model.collections.Data;

import java.util.List;

public interface TemplateService {

    List<Template> templateList();

    Template templateById(String id);

    List<ResponseTemplateNameDto> templateBriefList();

    List<ResponseTemplateNameDto> userActiveTemplateNameList(String userId);

    List<Template> userActiveTemplateList(String userId);

    List<Data> userSentTemplateList(String userId);

    List<Data> userCompletedTemplateList(String userId);

    Template activeTemplateForUserById(String userId, String templateId);

    Data sentTemplateForUserById(String userId, String templateId);

    List<ResponseDataBrief> userSentTemplateBriefList(String userId);

    List<ResponseDataBrief> userCompletedTemplateBriefList(String userId);

    Data completedTemplateListForUserById(String userId, String templateId);
}
