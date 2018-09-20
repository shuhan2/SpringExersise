package com.thoughtworks.springExercise.domain;

import java.util.List;

public class User {
    private int id;
    private String name;

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    private List<Contact> contacts;
    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public User(int id, String name, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
