package com.nikitin.mailapp.dao;

import com.nikitin.mailapp.model.Person;
import com.nikitin.mailapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {


    private static PersonRepository personRepository;

    @Autowired
    public PersonDAO(PersonRepository personRepository) {
        PersonDAO.personRepository = personRepository;
    }

    public static Iterable<Person> getPersons() {

        return personRepository.findAll();
    }

    public static void addPersons(Person person) {
        personRepository.save(person);

    }

    public static void deletePerson(Long person) {
        personRepository.deleteById(person);

    }

    public static Person getPerson(Long id) {
        Person person;
        person = personRepository.findById(id).orElseThrow();
        System.out.println(person.getName());
        System.out.println(person.getSurname());
        System.out.println(person.getEmail());

        return person;
    }

    public static void updatePerson(Person person) {
        personRepository.save(person);

    }

}

