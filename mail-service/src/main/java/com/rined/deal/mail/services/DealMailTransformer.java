package com.rined.deal.mail.services;

import com.rined.deal.mail.model.DestinationDto;
import org.springframework.mail.SimpleMailMessage;

public interface DealMailTransformer {

    SimpleMailMessage transform(DestinationDto destination);

}
