package com.rined.template.model.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Statistic {

    @JsonProperty(value = "count")
    private long templateCount;

    @JsonProperty(value = "providerCount")
    private long providerCount;

}
