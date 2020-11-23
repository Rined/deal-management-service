package com.rined.payment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @NotNull
    @JsonProperty("from")
    private String fromUser;

    @NotNull
    @JsonProperty("to")
    private String toUser;

    @NotNull
    @JsonProperty("amount")
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal amount;

}
