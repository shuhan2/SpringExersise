package com.thoughtworks.springExercise.repository;

import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;

import java.util.List;

public interface ConcatRepository {
    Contact createContactForUser(int id, Contact contact);

    List<Contact> findContacts(int id);
}
