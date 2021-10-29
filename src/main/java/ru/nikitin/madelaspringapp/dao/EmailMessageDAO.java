package ru.nikitin.madelaspringapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nikitin.madelaspringapp.model.EmailMessage;
import ru.nikitin.madelaspringapp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class EmailMessageDAO {
    private static String URL = "jdbc:postgresql://localhost:5432/MadelaDB";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "User19cfb4";

    private JavaMailSender emailSender;

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Autowired
    public EmailMessageDAO(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void sendEmail() {
        List<Person> emailList = new ArrayList<>();
        EmailMessage emailMessage = new EmailMessage();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setEmail(resultSet.getString("email"));

                emailList.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for(Person email : emailList) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmail());
            message.setText(emailMessage.getMessage());

            this.emailSender.send(message);
        }

    }
}
