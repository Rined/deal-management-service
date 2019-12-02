package com.rined.deal.mail.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailCreatorServiceImpl implements MailCreatorService {

    @Override
    public SimpleMailMessage create(String emailTo, String emailFrom, String mailTitle, String mailBody) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailTo);
        mailMessage.setFrom(emailFrom);
        mailMessage.setSubject(mailTitle);
        mailMessage.setText(mailBody);
        return mailMessage;
    }

}
