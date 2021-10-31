package ru.nikitin.madelaspringapp.dao;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailMessageDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/MadelaDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "User19cfb4";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<String> getEmail() {
        List<String> emailList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT email FROM person ";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                emailList.add(resultSet.getString("email"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return emailList;
    }
}

