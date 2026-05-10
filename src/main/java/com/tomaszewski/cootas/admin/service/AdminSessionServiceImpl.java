package com.tomaszewski.cootas.admin.service;

import org.springframework.stereotype.Service;

@Service
public class AdminSessionServiceImpl implements AdminSessionService {
    private final MailService mailService;

    public AdminSessionServiceImpl(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public String createSession(String email) {
        mailService.sendMail(email, "1");
        return "Session created successfully";
    }
}
