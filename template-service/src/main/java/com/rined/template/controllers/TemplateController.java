package com.rined.template.controllers;

import com.rined.template.controllers.dto.TemplateRequestDto;
import com.rined.template.controllers.dto.UserDto;
import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;
import com.rined.template.resolver.User;
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
    public List<Template> getAllTemplates(@User UserDto userDto) {
        return service.getAllTemplates(userDto);
    }

    @GetMapping("/brief")
    public List<TemplateBrief> getAllBriefTemplates(@User UserDto userDto) {
        return service.getAllBriefTemplates(userDto);
    }

    @GetMapping("/{templateId}")
    public Template getTemplateById(@PathVariable("templateId") String templateId,
                                    @User UserDto userDto) {
        return service.getTemplateById(templateId, userDto);
    }

    @DeleteMapping("/{templateId}")
    public void deleteById(@PathVariable("templateId") String templateId,
                           @User UserDto userDto) {
        service.deleteById(templateId, userDto);
    }

    @PutMapping("/{templateId}")
    public void updateTemplate(@PathVariable("templateId") String templateId,
                               @Valid @RequestBody TemplateRequestDto templateDto,
                               @User UserDto userDto) {
        service.updateTemplate(templateId, templateDto, userDto);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTemplate(@Valid @RequestBody TemplateRequestDto templateDto,
                               @User UserDto userDto){
        service.createTemplate(templateDto, userDto);
    }
}
