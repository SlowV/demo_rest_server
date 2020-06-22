package com.example.restdemo2.test;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String id;
    private String name;
    private List<Person> children = new ArrayList<>();

    public Person() {
    }

    public Person(String id, String name, List<Person> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children.addAll(children);
    }
}
