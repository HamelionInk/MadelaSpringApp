package com.nikitin.mailapp.service;

import com.nikitin.mailapp.dto.PersonDTO;
import com.nikitin.mailapp.model.Person;
import com.nikitin.mailapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements MyPersonService {
        private final PersonRepository personRepository;

        @Autowired
        public PersonService(PersonRepository personRepository) {
                this.personRepository = personRepository;
        }
        @Override
        public void createOrUpdatePerson(PersonDTO personDTO) {
                Person person = convertDtoToModel(personDTO);
                personRepository.save(person);
        }

        @Override
        public List<PersonDTO> showAllPerson() {
                List<Person> list = personRepository.findAll();
                List<PersonDTO> listDto = new ArrayList<>();
                for (Person person : list) {
                        PersonDTO personDTO = convertModelToDto(person);
                        listDto.add(personDTO);
                }
                return listDto;
        }

        @Override
        public void deletePerson(Long id) {
                personRepository.deleteById(id);
        }

        @Override
        public PersonDTO editPerson(Long id) {
                Person person = personRepository.getById(id);
                return convertModelToDto(person);
        }

        private Person convertDtoToModel(PersonDTO personDTO) {
                Person person = new Person();
                if(personDTO.getId() != null) {
                        person.setId(personDTO.getId());
                }
                person.setName(personDTO.getName());
                person.setSurname(personDTO.getSurname());
                person.setEmail(personDTO.getEmail());
                return person;
        }

        private PersonDTO convertModelToDto(Person person) {
                PersonDTO personDTO = new PersonDTO();
                personDTO.setId(person.getId());
                personDTO.setName(person.getName());
                personDTO.setSurname(person.getSurname());
                personDTO.setEmail(person.getEmail());
                return personDTO;
        }
}
