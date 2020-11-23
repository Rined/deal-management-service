package com.rined.deal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SagaDealRequest {

    private String dealId;

    private String proposalId;

    private SagaDealRequestType type;

}
