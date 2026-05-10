package com.tomaszewski.cootas.admin.service;

public interface MailService {
    void sendMail(String to, String subject, String text);
}
