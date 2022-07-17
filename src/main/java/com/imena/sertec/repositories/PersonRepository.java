package com.imena.sertec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imena.sertec.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByUsername(String username);
}
