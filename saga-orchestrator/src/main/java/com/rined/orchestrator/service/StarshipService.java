package com.rined.orchestrator.service;

import com.rined.orchestrator.dto.StarshipDto;

public interface StarshipService {

    StarshipDto save(StarshipDto dto);

    void send(StarshipDto dto);

    void consume(StarshipDto dto);

}
