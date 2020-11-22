package com.rined.orchestrator.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StarshipDto extends AbstractDto {
    private String name;
    private String model;
}