package com.rined.template.controllers;

import com.rined.template.controllers.dto.TemplateRequestDto;
import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;
import com.rined.template.services.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService service;

    @GetMapping("/")
    public List<Template> getAllTemplates() {
        return service.getAllTemplates();
    }

    @GetMapping("/brief")
    public List<TemplateBrief> getAllBriefTemplates() {
        return service.getAllBriefTemplates();
    }

    @GetMapping("/{templateId}")
    public Template getTemplateById(@PathVariable("templateId") String templateId) {
        return service.getTemplateById(templateId);
    }

    @DeleteMapping("/{templateId}")
    public void deleteById(@PathVariable("templateId") String templateId) {
        service.deleteById(templateId);
    }

    @PutMapping("/{templateId}")
    public void updateTemplate(@PathVariable("templateId") String templateId,
                               @Valid @RequestBody TemplateRequestDto templateDto) {
        service.updateTemplate(templateId, templateDto);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTemplate(@Valid @RequestBody TemplateRequestDto templateDto){
        service.createTemplate(templateDto);
    }
}
