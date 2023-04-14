package com.example.coursework.services;

import com.example.coursework.models.Person;
import com.example.coursework.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person findByLogin(Person person) {
        Optional<Person> personDb = personRepository.findByLogin(person.getLogin());
        return personDb.orElse(null);
    }

    public Person getPersonById(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElse(null);
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }

    @Transactional
    public void updatePersonRole(int id) {
        Optional<Person> updatedPerson = personRepository.findById(id);
        if (updatedPerson.isPresent()) {
            Person newPerson = updatedPerson.get();
            switch (newPerson.getRole()) {
                case "ROLE_ADMIN" -> newPerson.setRole("ROLE_USER");
                case "ROLE_USER" -> newPerson.setRole("ROLE_ADMIN");
            }
            personRepository.save(newPerson);
        }
    }

    public List<Person> getAllPersonsSortedAscById() {
        return personRepository.findAllPersonsSortedAscById();
    }
}
