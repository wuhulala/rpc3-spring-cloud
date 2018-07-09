package com.wuhulala.person.controller;


import com.wuhulala.person.dao.PersonRepository;
import com.wuhulala.person.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author wuhulala
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> savePerson(@RequestBody String personName) {
        logger.info("save[" + personName + "]");
        Person p = new Person(personName);
        personRepository.save(p);
        List<Person> people = personRepository.findAll(new PageRequest(0, 10)).getContent();
        return people;
    }

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        logger.debug("客户端请求id=[" + id + "]");
        Optional<Person> result =  personRepository.findById(id);
        return result.orElseGet(Person::new);
    }

    @GetMapping(value = "/person")
    public Person getPerson(@RequestBody Person person) {
        logger.debug("客户端请求[" + person + "]");
        Optional<Person> result =  personRepository.findById(person.getId());
        return result.orElseGet(Person::new);
    }

}
