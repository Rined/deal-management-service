package com.rined.client.controllers;

import com.rined.client.dto.response.ResponseTemplateNameDto;
import com.rined.client.model.DocumentTemplate;
import com.rined.client.model.FilledTemplateData;
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
    public List<DocumentTemplate> templateList() {
        return templateService.templateList();
    }

    @GetMapping("/templates/{id}")
    public DocumentTemplate templateById(@PathVariable("id") String id) {
        return templateService.templateById(id);
    }

    @GetMapping("/templates/brief")
    public List<ResponseTemplateNameDto> templateBriefList() {
        return templateService.templateBriefList();
    }

    @GetMapping("/users/{userId}/active/brief")
    public List<ResponseTemplateNameDto> getActiveTemplatesNameForUser(@PathVariable("userId") String userId) {
        return templateService.userActiveTemplateNameList(userId);
    }

    @GetMapping("/users/{userId}/active")
    public List<DocumentTemplate> getActiveTemplatesForUser(@PathVariable("userId") String userId) {
        return templateService.userActiveTemplateList(userId);
    }

    @GetMapping("/users/{userId}/sent")
    public List<FilledTemplateData> getSentDocumentTemplatesForUser(@PathVariable("userId") String userId) {
        return templateService.userSentTemplateList(userId);
    }

    @GetMapping("/users/{userId}/completed")
    public List<FilledTemplateData> getCompletedDocumentTemplatesForUser(@PathVariable("userId") String userId) {
        return templateService.userCompletedTemplateList(userId);
    }
}
