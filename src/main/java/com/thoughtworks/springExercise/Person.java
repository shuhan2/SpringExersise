package com.thoughtworks.springExercise;

public class Person implements Comparable<Person> {
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person otherPerson) {
        if (age.equals(otherPerson.age)) {
            return name.compareTo(otherPerson.name);
        }
        return age.compareTo(otherPerson.age);
    }
}
