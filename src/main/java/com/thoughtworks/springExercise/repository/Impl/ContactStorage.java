package com.thoughtworks.springExercise.repository.Impl;

import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContactStorage {
    static final Map<Integer, Contact> CONTACTS = new HashMap<>();
    static {
        CONTACTS.put(1, new Contact(1, "zhang san", 123456, "female", 18));
        CONTACTS.put(2, new Contact(2, "li si", 2521371, "female", 16));
    }

    public static void clear() {
        CONTACTS.clear();
    }

    public static void add(Contact...contacts) {
        Arrays.stream(contacts).forEach(user -> CONTACTS.put(user.getId(), user));
    }

    public static Contact getById(int id) {
        return CONTACTS.get(id);
    }

    public static Map<Integer, Contact> getContacts() {
        return CONTACTS;
    }
}
