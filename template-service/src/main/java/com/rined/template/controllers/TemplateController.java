package com.rined.template.controllers;

import com.rined.template.model.Template;
import com.rined.template.model.TemplateBrief;
import com.rined.template.model.dto.TemplateRequestDto;
import com.rined.template.model.dto.UserDto;
import com.rined.template.resolver.User;
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
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "/api", description = "Операции с шаблонами пользователя")
public class TemplateController {

    private final TemplateService service;

    @GetMapping("/templates")
    @ApiOperation(value = "Получить шаблоны пользователя")
    public List<Template> getAllTemplates(@ApiIgnore @User UserDto userDto) {
        return service.getAllTemplates(userDto);
    }

    @GetMapping("/templates/brief")
    @ApiOperation(value = "Получить сокращенные шаблоны пользователя")
    public List<TemplateBrief> getAllBriefTemplates(@ApiIgnore @User UserDto userDto) {
        return service.getAllBriefTemplates(userDto);
    }

    @GetMapping("/templates/{templateId}")
    @ApiOperation(value = "Получить шаблон пользователя по id")
    public Template getTemplateById(@PathVariable("templateId") String templateId,
                                    @ApiIgnore @User UserDto userDto) {
        return service.getTemplateById(templateId, userDto);
    }

    @DeleteMapping("/templates/{templateId}")
    @ApiOperation(value = "Удалить шаблон пользователя по id")
    public void deleteById(@PathVariable("templateId") String templateId,
                           @ApiIgnore @User UserDto userDto) {
        service.deleteById(templateId, userDto);
    }

    @PutMapping("/templates/{templateId}")
    @ApiOperation(value = "Изменить шаблон пользователя по id")
    public void updateTemplate(@PathVariable("templateId") String templateId,
                               @Valid @RequestBody TemplateRequestDto templateDto,
                               @ApiIgnore @User UserDto userDto) {
        service.updateTemplate(templateId, templateDto, userDto);
    }

    @PostMapping("/templates")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Создать шаблон пользователя")
    public void createTemplate(@Valid @RequestBody TemplateRequestDto templateDto,
                               @ApiIgnore @User UserDto userDto) {
        service.createTemplate(templateDto, userDto);
    }
}
