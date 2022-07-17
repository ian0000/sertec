package com.imena.sertec.security;

import java.util.List;
import java.util.Optional;

import com.imena.sertec.security.SecurityPerson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imena.sertec.model.Person;
import com.imena.sertec.repositories.PersonRepository;
import com.imena.sertec.services.PersonService;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class PersonServiceImpl implements PersonService,
        UserDetailsService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> getPersons() {
        log.info("Listar accesorios");
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        log.info("Buscar accesorio con id {}", id);
        return personRepository.findById(id);
    }

    @Override
    public Person insert(Person person) {
        log.info("Se han agregado los datos {}", person);
        return personRepository.save(person);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = this.personRepository.findByUsername(username);
        SecurityPerson securityPerson = new SecurityPerson(person);

        return securityPerson;
    }
}
