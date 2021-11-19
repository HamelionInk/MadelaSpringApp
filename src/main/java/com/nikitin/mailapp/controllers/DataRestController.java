package com.nikitin.mailapp.controllers;

import com.nikitin.mailapp.dto.PersonDTO;
import com.nikitin.mailapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataRestController {

    private PersonService personService;

    @Autowired
    public DataRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/api/show")
    public List<PersonDTO> showAllPerson() {
        return personService.showAllPerson();
    }

    @GetMapping("/{id}")
    public PersonDTO showPersonById(@PathVariable("id") Long id) {
        return personService.showPerson(id);
    }

    @PutMapping("/api/{id}")
    public void updatePerson(String name, String surname, String email) {
        PersonDTO personDTO = new PersonDTO(name, surname, email);
        personService.createOrUpdatePerson(personDTO);
    }

    @DeleteMapping("/api/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
    }
}
