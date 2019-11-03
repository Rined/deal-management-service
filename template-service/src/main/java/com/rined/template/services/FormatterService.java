package com.rined.template.services;

import com.rined.template.controllers.dto.FormatterRequestDto;
import com.rined.template.model.Formatter;
import com.rined.template.model.FormatterBrief;

import java.util.List;

public interface FormatterService {

    List<Formatter> getFormatters();

    Formatter getFormatterById(String id);

    void deleteById(String id);

    void createFormatter(FormatterRequestDto requestDto);

    void updateFormatter(String id, FormatterRequestDto requestDto);

    List<FormatterBrief> getBriefFormatters();
}
