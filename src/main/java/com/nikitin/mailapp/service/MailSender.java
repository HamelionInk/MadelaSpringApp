package com.nikitin.mailapp.service;

import com.nikitin.mailapp.model.Person;
import com.nikitin.mailapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class MailSender implements MyMailSender {
    private final JavaMailSender emailSender;
    private final PersonRepository personRepository;

    @Autowired
    public MailSender(JavaMailSender emailSender, PersonRepository personRepository) {
        this.emailSender = emailSender;
        this.personRepository = personRepository;
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void sendEmail() {
        List<Person> emailList = this.getEmail();
        for (Person person : emailList) {
            this.emailSender.send(createEmail(person.getEmail(), "Test Spring", "Пора на работу!"));
        }
    }

    public SimpleMailMessage createEmail(String emailTo, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("username");
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        return simpleMailMessage;
    }

    public List<Person> getEmail() {
        List<Person> personList = personRepository.findAll();
        return personList;
    }
}
