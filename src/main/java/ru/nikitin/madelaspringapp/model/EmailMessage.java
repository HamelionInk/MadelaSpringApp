package ru.nikitin.madelaspringapp.model;

public class EmailMessage {

    private final String message;

    public EmailMessage() {
        message = "Пора на работу!";
    }

    public String getMessage() {
        return message;
    }
}
