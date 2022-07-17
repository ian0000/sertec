package com.imena.sertec.services;

import java.util.List;
import java.util.Optional;

import com.imena.sertec.model.Person;

public interface PersonService {
    List<Person> getPersons();

    Optional<Person> getPersonById(int id);

    Person insert(Person person);
}