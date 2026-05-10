package com.tomaszewski.cootas.admin.service;

import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

    @Override
    public void sendMail(String to, String sessionId) {
        System.out.println("Sending email to " + to);
    }
}
