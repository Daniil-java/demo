package ru.spring.demo.Trash;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class PersonController {
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person("Daniel", 25));
        people.add(new Person("Andrey", 30));
        people.add(new Person("Boris", 20));
        people.add(new Person("Oleg", 50));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(String name) {
        return people.stream().filter(person -> person.getName() == name).findAny().orElse(null);
    }

    public void setPerson(String name, int age) {
        people.add(new Person(name, age));
    }

    public void setPerson(Person person) {
        people.add(person);
    }

    public void delete(Person person) {
        people.remove(0);
    }

    public void put(Person person) {
        people.set(0, person);
    }
}
