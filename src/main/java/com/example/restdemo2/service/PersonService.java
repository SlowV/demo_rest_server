package com.example.restdemo2.service;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Page<Person> getAll(Specification<Person> specification, int page, int limit) {
        return personRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public Person getById(Long id) {
        return personRepository.findById(id).orElse(null);
    }
}
