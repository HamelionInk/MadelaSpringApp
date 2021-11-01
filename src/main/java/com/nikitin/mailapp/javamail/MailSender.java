package com.nikitin.mailapp.javamail;

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

    @Autowired
    public MailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    //@Scheduled(cron = "0 0 8 * * *")
    @Scheduled(fixedRate = 5000)
    public void sendEmail() {
        List<String> emailList = new MailMessageDAO().getEmail();
        for (String email : emailList) {
            System.out.println(email);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setText("Пора на работу!");

            this.emailSender.send(message);
        }
    }
}
