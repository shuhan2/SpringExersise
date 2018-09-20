package com.thoughtworks.springExercise.repository.Impl;

import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.ConcatRepository;

import java.util.List;

public class ConcatRepositoryImpl implements ConcatRepository {
    @Override
    public Contact createContactForUser(int id, Contact contact) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user = userRepository.findById(id);
        List<Contact> contacts = user.getContacts();
        contacts.add(contact);
        user.setContacts(contacts);
        return contact;

    }
}
