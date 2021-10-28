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

    private static int PERSON_COUNT;

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

    public static int getPersonCount() {
        return PERSON_COUNT;
    }

    public static List<Person> getPersons() {
        List<Person> personList = new ArrayList<>();

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

                personList.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;

    }

    public static void addPerson(Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO person VALUES(?, ?, ?, ?)");

            preparedStatement.setInt(1, ++PERSON_COUNT);
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getSurname());
            preparedStatement.setString(4, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deletePerson(int person) {
        System.out.println(person);

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM person WHERE id=?");
            preparedStatement.setInt(1, person);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

