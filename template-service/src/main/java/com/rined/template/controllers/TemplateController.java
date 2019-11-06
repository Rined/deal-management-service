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
@CrossOrigin(origins = "http://localhost:9000")
public class TemplateController {

    private final TemplateService service;

    @GetMapping("/templates")
    public List<Template> getAllTemplates() {
        return service.getAllTemplates();
    }

    @GetMapping("/templates/brief")
    public List<TemplateBrief> getAllBriefTemplates() {
        return service.getAllBriefTemplates();
    }

    @GetMapping("/templates/{templateId}")
    public Template getTemplateById(@PathVariable("templateId") String templateId) {
        return service.getTemplateById(templateId);
    }

    @DeleteMapping("/templates/{templateId}")
    public void deleteById(@PathVariable("templateId") String templateId) {
        service.deleteById(templateId);
    }

    @PutMapping("/templates/{templateId}")
    public void updateTemplate(@PathVariable("templateId") String templateId,
                               @Valid @RequestBody TemplateRequestDto templateDto) {
        service.updateTemplate(templateId, templateDto);
    }

    @PostMapping("/templates")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTemplate(@Valid @RequestBody TemplateRequestDto templateDto){
        service.createTemplate(templateDto);
    }
}
