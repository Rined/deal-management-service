package com.rined.deal.mail.services;

import org.springframework.mail.SimpleMailMessage;

public interface MailCreatorService {

    SimpleMailMessage create(String emailTo, String emailFrom, String mailTitle, String mailBody);

}
