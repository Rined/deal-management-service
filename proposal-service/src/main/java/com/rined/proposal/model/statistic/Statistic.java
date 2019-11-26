package com.rined.proposal.model.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Statistic {

    @JsonProperty(value = "count")
    private long proposalCount;

    @JsonProperty(value = "userCount")
    private long userCount;

}
