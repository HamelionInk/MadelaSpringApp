package com.nikitin.mailapp.repository;

import com.nikitin.mailapp.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
