package com.nikitin.mailapp.dao;

import com.nikitin.mailapp.model.Person;
import com.nikitin.mailapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MailMessageDAO {

    private final PersonRepository personRepository;

    @Autowired
    public MailMessageDAO(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<String> getEmail() {
        List<String> emailList = new ArrayList<>();
        Iterable<Person> personList = personRepository.findAll();

        for (Person person : personList) {
            emailList.add(person.getEmail());
        }

        return emailList;
    }

}

