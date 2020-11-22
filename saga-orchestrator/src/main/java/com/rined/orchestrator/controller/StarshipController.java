package com.rined.orchestrator.controller;

import com.rined.orchestrator.dto.StarshipDto;
import com.rined.orchestrator.service.StarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/starship")
@RequiredArgsConstructor
public class StarshipController {
    private final StarshipService service;

    @PostMapping
    public void send(@RequestBody StarshipDto dto) {
        service.send(dto);
    }

}