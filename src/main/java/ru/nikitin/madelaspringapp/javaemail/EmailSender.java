package ru.nikitin.madelaspringapp.javaemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nikitin.madelaspringapp.dao.EmailMessageDAO;

import java.util.List;

@Component
@EnableScheduling
public class EmailSender {
    private final List<String> emailList;
    private final JavaMailSender emailSender;
    private final EmailMessageDAO emailMessageDAO;

    @Autowired
    public EmailSender(JavaMailSender emailSender, EmailMessageDAO emailMessageDAO) {
        this.emailMessageDAO = emailMessageDAO;
        this.emailSender = emailSender;
        emailList = this.emailMessageDAO.getEmail();
    }

    //@Scheduled(cron = "0 0 8 * * *")
    @Scheduled(fixedRate = 5000)
    public void sendEmail() {
        for (String email : emailList) {
            System.out.println(email);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setText("Пора на работу!");

            this.emailSender.send(message);
        }
    }
}
