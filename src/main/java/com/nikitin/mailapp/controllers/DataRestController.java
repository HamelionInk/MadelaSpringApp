package com.nikitin.mailapp.controllers;

import com.nikitin.mailapp.dto.PersonDTO;
import com.nikitin.mailapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataRestController {

    private PersonService personService;

    @Autowired
    public DataRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> showAllPerson() {
        return personService.showAllPerson();
    }

    @GetMapping("/{id}")
    public PersonDTO showPersonById(@PathVariable("id") Long id) {
        return personService.editPerson(id);
    }

    public void updatePerson() {

    }

    public void deletePerson() {

    }
}
