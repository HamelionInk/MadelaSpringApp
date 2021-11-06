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
public class MailSender {
    private final JavaMailSender emailSender;
    private final PersonRepository personRepository;

    @Autowired
    public MailSender(JavaMailSender emailSender, PersonRepository personRepository) {
        this.emailSender = emailSender;
        this.personRepository = personRepository;
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void sendEmail() {
        List<String> emailList = this.getEmail();
        for (String email : emailList) {
            this.emailSender.send(createEmail(email, "Test Spring", "Пора на работу!"));
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

    private List<String> getEmail() {
        List<String> emailList = new ArrayList<>();
        Iterable<Person> personList = personRepository.findAll();

        for (Person person : personList) {
            emailList.add(person.getEmail());
        }

        return emailList;
    }
}
