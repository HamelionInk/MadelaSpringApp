package com.nikitin.mailapp.service;

import com.nikitin.mailapp.dto.PersonDTO;

import java.util.List;

public interface MyPersonService {
    public void createOrUpdatePerson(PersonDTO personDTO);

    public List<PersonDTO> showAllPerson();

    public void deletePerson(Long id);

    public PersonDTO editPerson(Long id);
}
