package ru.nikitin.madelaspringapp.dao;

import org.springframework.stereotype.Component;
import ru.nikitin.madelaspringapp.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static String URL = "jdbc:postgresql://localhost:5432/MadelaDB";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "User19cfb4";

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

    public static List<Person> getPersons() {
        List<Person> personList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM persons";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;

    }
}

