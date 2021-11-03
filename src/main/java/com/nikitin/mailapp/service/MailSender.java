package com.nikitin.mailapp.service;

import com.nikitin.mailapp.dao.MailMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class MailSender {
    private final JavaMailSender emailSender;
    private final MailMessageDAO mailMessageDAO;
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    public MailSender(JavaMailSender emailSender, MailMessageDAO mailMessageDAO) {
        this.emailSender = emailSender;
        this.mailMessageDAO = mailMessageDAO;
    }

    @Scheduled(cron = "0 0 8 * * *")
    //@Scheduled(fixedRate = 5000)
    public void sendEmail() {
        List<String> emailList = mailMessageDAO.getEmail();
        for (String email : emailList) {
            this.emailSender.send(createEmail(email, "Test Spring", "Пора на работу!"));
        }
    }

    public SimpleMailMessage createEmail(String emailTo, String subject, String message) {
        simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("username");
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        return simpleMailMessage;
    }
}
