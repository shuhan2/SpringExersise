package com.thoughtworks.springExercise.repository;

import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;

import java.util.List;
import java.util.Map;

public interface ContactRepository {
    Contact createContactForUser(int id, Contact contact);

    Map<Integer, Contact> findContacts(int id);

    Contact updateContact(int userId, int id, Contact contact);

    void deleteContact(int userId, int contactId);
}
