package com.rined.template.controllers;

import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;
import com.rined.template.model.dto.TemplateCreateRequestDto;
import com.rined.template.model.dto.TemplateRequestDto;
import com.rined.template.model.dto.ProviderDto;
import com.rined.template.resolver.Provider;
import com.rined.template.services.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "/api", description = "Операции с шаблонами поставщика")
public class TemplateController {
    private final TemplateService service;

    @GetMapping("/templates")
    @ApiOperation(value = "Получить шаблоны поставщика")
    public List<Template> getAllProviderTemplates(@ApiIgnore @Provider ProviderDto providerDto) {
        return service.getAllTemplates(providerDto);
    }

    @GetMapping("/templates/brief")
    @ApiOperation(value = "Получить сокращенные шаблоны поставщика")
    public List<TemplateBrief> getAllProviderBriefTemplates(@ApiIgnore @Provider ProviderDto providerDto) {
        return service.getAllBriefTemplates(providerDto);
    }

    @GetMapping("/templates/{templateId}")
    @ApiOperation(value = "Получить шаблон поставщика по id")
    public Template getProviderTemplate(@PathVariable("templateId") String templateId,
                                        @ApiIgnore @Provider ProviderDto providerDto) {
        return service.getTemplateById(templateId, providerDto);
    }

    @DeleteMapping("/templates/{templateId}")
    @ApiOperation(value = "Удалить шаблон поставщика по id")
    public void deleteProviderTemplate(@PathVariable("templateId") String templateId,
                                       @ApiIgnore @Provider ProviderDto providerDto) {
        service.deleteById(templateId, providerDto);
    }

    @PutMapping("/templates/{templateId}")
    @ApiOperation(value = "Изменить шаблон поставщика по id")
    public Template updateProviderTemplate(@PathVariable("templateId") String templateId,
                                           @Valid @RequestBody TemplateRequestDto templateDto,
                                           @ApiIgnore @Provider ProviderDto providerDto) {
        return service.updateTemplate(templateId, templateDto, providerDto);
    }

    @PostMapping("/templates")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Создать шаблон поставщика")
    public Template createProviderTemplate(@Valid @RequestBody TemplateCreateRequestDto templateDto,
                                           @ApiIgnore @Provider ProviderDto providerDto) {
        return service.createTemplate(templateDto, providerDto);
    }
}
