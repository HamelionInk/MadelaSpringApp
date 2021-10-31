package ru.nikitin.madelaspringapp.dao;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.Assert;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailMessageDAOTest {

    JavaMailSender emailSender;

    @Test
    void getEmailTest() {
        System.out.println("test getEmail");

    }

    @Test
    void sendEmailTest() {

        //Проверяет отправку сообщений классом mailSender
    }
}

