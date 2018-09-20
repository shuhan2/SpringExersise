package com.thoughtworks.springExercise.domain;

public class Contact {
    private int id;
    private String name;
    private int phoneNumber;
    private String gender;
    private int age;

    public Contact(int id, String name, int phoneNumber, String gender, int age) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }




}
