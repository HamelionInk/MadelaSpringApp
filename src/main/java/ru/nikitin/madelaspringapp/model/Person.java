package ru.nikitin.madelaspringapp.model;

public class Person {
    private int id;
    private String name;
    private String surname;
    private int numberPhone;
    private String email;

    public Person(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person() {

    }

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setNumberPhone(int numberPhone) {
        this.numberPhone = numberPhone;
    }

    public int getNumberPhone() {
        return numberPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
