package com.nikitin.mailapp;

import com.nikitin.mailapp.model.Person;
import com.nikitin.mailapp.repository.PersonRepository;
import com.nikitin.mailapp.service.MailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MailSenderTest {

    private final MailSender mailSender;

    @MockBean
    private final JavaMailSender javaMailSender;

    @MockBean
    private final PersonRepository personRepository;

    @Autowired
    public MailSenderTest(PersonRepository personRepository, JavaMailSender javaMailSender, MailSender mailSender) {
        this.mailSender = mailSender;
        this.javaMailSender = javaMailSender;
        this.personRepository = personRepository;

    }

    @Test
    void sendMail() {
        String subject = "Test Spring";
        String message = "Пора на работу!";
        List<Person> emailTest = new ArrayList<>();
        Person person = new Person();
        person.setEmail("test@gmail.com");
        emailTest.add(person);
        when(personRepository.findAll()).thenReturn(emailTest);
        mailSender.sendEmail();
        verify(javaMailSender, times(1)).send(mailSender.createEmail(emailTest.get(0).getEmail(), subject, message));
    }

    @Test
    void createMailTest() {
        String emailTo = "heh@gmail.com";
        String subject = "Subject Text";
        String message = "Message Text";
        String[] emailTest;
        SimpleMailMessage mail = mailSender.createEmail(emailTo, subject, message);
        assertEquals(mail.getText(), message);
        emailTest = mail.getTo();
        assertEquals(emailTest[0], emailTo);
        assertEquals(mail.getSubject(), subject);

    }

}
