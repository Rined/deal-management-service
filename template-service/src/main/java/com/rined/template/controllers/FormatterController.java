package com.rined.template.controllers;

import com.rined.template.controllers.dto.FormatterRequestDto;
import com.rined.template.model.Formatter;
import com.rined.template.model.FormatterBrief;
import com.rined.template.services.FormatterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FormatterController {

    private final FormatterService service;

    @GetMapping("/formatters")
    public List<Formatter> getAllFormatters() {
        return service.getFormatters();
    }

    @GetMapping("/formatters/brief")
    public List<FormatterBrief> getAllBriefFormatters() {
        return service.getBriefFormatters();
    }

    @GetMapping("/formatters/{id}")
    public Formatter getFormatterById(@PathVariable("id") String id) {
        return service.getFormatterById(id);
    }

    @DeleteMapping("/formatters/{id}")
    public void deleteFormatter(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    @PostMapping("/formatters")
    public void createFormatter(@Valid @RequestBody FormatterRequestDto requestDto) {
        service.createFormatter(requestDto);
    }

    @PutMapping("/formatters/{id}")
    public void changeFormatter(@PathVariable("id") String id,
                                @Valid @RequestBody FormatterRequestDto requestDto) {
        service.updateFormatter(id, requestDto);
    }
}
