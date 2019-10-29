package com.rined.client.controllers;

import com.rined.client.dto.response.ResponseDataBrief;
import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.model.collections.Template;
import com.rined.client.model.collections.Data;
import com.rined.client.services.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping("/templates")
    public List<Template> templateList() {
        return templateService.templateList();
    }

    @GetMapping("/templates/{id}")
    public Template templateById(@PathVariable("id") String id) {
        return templateService.templateById(id);
    }

    @GetMapping("/templates/brief")
    public List<ResponseTemplateNameDto> templateBriefList() {
        return templateService.templateBriefList();
    }

    @GetMapping("/users/{userId}/templates/active")
    public List<Template> getActiveTemplatesForUser(@PathVariable("userId") String userId) {
        return templateService.userActiveTemplateList(userId);
    }

    //todo
    @GetMapping("/users/{userId}/templates/active/{templateId}")
    public Template getActiveTemplateForUserById(@PathVariable("userId") String userId,
                                                 @PathVariable("templateId") String templateId) {
        return templateService.activeTemplateForUserById(userId, templateId);
    }

    @GetMapping("/users/{userId}/templates/active/brief")
    public List<ResponseTemplateNameDto> getActiveTemplatesBriefForUser(@PathVariable("userId") String userId) {
        return templateService.userActiveTemplateNameList(userId);
    }

    @GetMapping("/users/{userId}/templates/sent")
    public List<Data> getSentTemplatesForUser(@PathVariable("userId") String userId) {
        return templateService.userSentTemplateList(userId);
    }

    //todo
    @GetMapping("/users/{userId}/templates/sent/{templateId}")
    public Data getSentTemplateForUserById(@PathVariable("userId") String userId,
                                           @PathVariable("templateId") String templateId) {
        return templateService.sentTemplateForUserById(userId, templateId);
    }

    //todo
    @GetMapping("/users/{userId}/templates/sent/brief")
    public List<ResponseDataBrief> getSentTemplatesBriefForUser(@PathVariable("userId") String userId) {
        return templateService.userSentTemplateBriefList(userId);
    }

    @GetMapping("/users/{userId}/templates/completed")
    public List<Data> getCompletedTemplatesForUser(@PathVariable("userId") String userId) {
        return templateService.userCompletedTemplateList(userId);
    }

    //todo
    @GetMapping("/users/{userId}/templates/completed/{templateId}")
    public Data getCompletedTemplatesForUser(@PathVariable("userId") String userId,
                                             @PathVariable("templateId") String templateId) {
        return templateService.completedTemplateListForUserById(userId, templateId);
    }

    //todo
    @GetMapping("/users/{userId}/templates/completed/brief")
    public List<ResponseDataBrief> getCompletedTemplatesBriefForUser(@PathVariable("userId") String userId) {
        return templateService.userCompletedTemplateBriefList(userId);
    }
}
