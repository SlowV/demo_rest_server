package com.example.restdemo2.specification.singular;

import com.example.restdemo2.domain.Person;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Person.class)
public class Person_ {
    public static volatile SingularAttribute<Person, Integer> id;
}
