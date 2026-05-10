package com.tomaszewski.cootas.admin.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private final static String SENDER = "noreply@cootas.io";

    private final MailSender mailSender;

    public MailServiceImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(SENDER);
        message.setSubject(subject);
        message.setText(text);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
