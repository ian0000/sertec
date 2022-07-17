package com.imena.sertec.controller;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imena.sertec.model.Person;
import com.imena.sertec.model.Roles;
import com.imena.sertec.model.State;
import com.imena.sertec.services.PersonService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/usr")
@NoArgsConstructor
@AllArgsConstructor
public class UserControl {

    @Autowired
    PersonService personService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String init(Model model) {
        log.info("cargar usuarios");

        model.addAttribute("usr", personService.getPersons());
        return "userListView";
    }

    @GetMapping("/addUser")
    public String addUSRview(Model model) {
        log.info("abrir usuarios y generar modelo para crear");
        model.addAttribute("addUsr", new Person());
        model.addAttribute("rol", Roles.values());
        return "addUser";
    }

    @PostMapping("/save")
    public String save(@Validated Person person, Model model) {

        try {
            person.setName(person.getName().toUpperCase());
            person.setLastName(person.getLastName().toUpperCase());
            String code = person.getName().charAt(0) + "" + person.getLastName().charAt(0);
            String usr = code + person.getPersonId();
            person.setCode(code);
            person.setUsername(usr);

            log.info("{} CHECK PERSON", person);
            if (person.getPassword() == null) {
                //esta parte cambiar a una clave random
                String pass = generateRandomString(6);
                log.error(pass);
                person.setPassword(passwordEncoder.encode(pass));
            }
            log.info("{} persona guardada", person);
            personService.insert(person);
        } catch (Exception e) {
            log.error("hubo error al guardar persona" + e);
        }
        return "redirect:/usr";
    }

    public String generateRandomString(int length) {
        final String INPUT =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        final Random RANDOM = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(INPUT.charAt(RANDOM.nextInt(INPUT.length())));
        }
        return builder.toString();
    }

    @GetMapping("/edit/{id}")
    public String editType(@PathVariable int id, Model model) {
        Optional<Person> person = personService.getPersonById(id);
        model.addAttribute("addUsr", person);
        model.addAttribute("rol", Roles.values());
        return "/addUser";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        Optional<Person> person = personService.getPersonById(id);
        if (!person.isEmpty()) {
            if (person.get().getState() == State.DESACTIVADO) {
                person.get().setState(State.ACTIVO);
            } else {
                person.get().setState(State.DESACTIVADO);
            }
        }
        try {
            if (person.get().getName() != "") {
                personService.insert(person.get());
            }
        } catch (Exception e) {
            log.error("error al modificar estado de persona");
        }
        return "redirect:/usr";
    }
}
