package com.rined.deal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class DealCreateRequestDto extends DealRequestDto {

    @JsonProperty("id")
    @NotNull(message = "Deal id is mandatory")
    @NotBlank(message = "Deal id is mandatory")
    private String id;


    @JsonProperty("price")
    @NotNull(message = "Deal price is mandatory")
    private BigDecimal price;
}
