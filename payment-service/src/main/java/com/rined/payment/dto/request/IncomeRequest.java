package com.rined.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IncomeRequest {

    @NotNull
    @JsonProperty("amount")
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal amount;

}
