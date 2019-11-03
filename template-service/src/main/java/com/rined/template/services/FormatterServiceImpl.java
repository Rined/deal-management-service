package com.rined.template.services;

import com.rined.template.controllers.dto.FormatterRequestDto;
import com.rined.template.converter.FormatterConverter;
import com.rined.template.exception.NotFoundException;
import com.rined.template.model.Formatter;
import com.rined.template.model.FormatterBrief;
import com.rined.template.repositories.FormatterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormatterServiceImpl implements FormatterService {

    private final FormatterRepository repository;
    private final FormatterConverter converter;

    @Override
    public List<Formatter> getFormatters() {
        return repository.findAll();
    }

    @Override
    public Formatter getFormatterById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Formatter with id %s not found!", id));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void createFormatter(FormatterRequestDto requestDto) {
        Formatter formatter = converter.convertRequestDtoToBean(requestDto);
        repository.save(formatter);
    }

    @Override
    public void updateFormatter(String id, FormatterRequestDto requestDto) {
        Formatter formatter = converter.convertRequestDtoToBean(requestDto);
        formatter.setId(id);
        repository.save(formatter);
    }

    @Override
    public List<FormatterBrief> getBriefFormatters() {
        return repository.getBriefFormatters();
    }
}
