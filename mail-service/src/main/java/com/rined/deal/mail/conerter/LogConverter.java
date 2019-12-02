package com.rined.deal.mail.conerter;

import com.rined.deal.mail.model.DestinationDto;
import com.rined.deal.mail.model.Log;
import org.springframework.messaging.MessageHeaders;

public interface LogConverter {

    Log convert(DestinationDto destinationDto, MessageHeaders messageHeaders);

}
