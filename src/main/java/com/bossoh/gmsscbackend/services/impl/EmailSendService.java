package com.bossoh.gmsscbackend.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("emailSendService")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailSendService {
    JavaMailSender javaMailSender;

    @Autowired
    public EmailSendService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

}