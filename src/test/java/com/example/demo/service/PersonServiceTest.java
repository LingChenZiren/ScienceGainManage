package com.example.demo.service;

import com.example.demo.pojo.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
PersonService service = new PersonService();
    @Test
    void findAll() {
        ArrayList<Person> people = service.findAll();
        for (Person person: people){
            System.out.println(person);
        }
    }
}