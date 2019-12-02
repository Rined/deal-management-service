package com.rined.deal.repositories.projection;

import com.rined.deal.model.DealState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealWrap {

    private DealState state;

}
