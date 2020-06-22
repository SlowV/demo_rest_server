package com.example.restdemo2.specification.singular;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.domain.Task;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Task.class)
public class Task_ {
    public static volatile SingularAttribute<Task, Person> person;
}
