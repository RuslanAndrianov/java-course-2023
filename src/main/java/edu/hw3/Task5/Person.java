package edu.hw3.Task5;

public class Person {

    public Person(String name, String surname) {
        setName(name);
        setSurname(surname);
    }

    private String name;

    private String surname;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
