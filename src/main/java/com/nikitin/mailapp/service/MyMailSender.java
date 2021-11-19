package com.nikitin.mailapp.service;

import com.nikitin.mailapp.model.Person;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public interface MyMailSender {
    void sendEmail();

    SimpleMailMessage createEmail(String emailTo, String subject, String message);

    List<Person> getEmail();
}
