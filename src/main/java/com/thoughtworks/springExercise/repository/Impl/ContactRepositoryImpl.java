package com.thoughtworks.springExercise.repository.Impl;

import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.ContactRepository;

import java.util.List;
import java.util.Map;

public class ContactRepositoryImpl implements ContactRepository {
    @Override
    public Contact createContactForUser(int id, Contact contact) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user = userRepository.findById(id);
        Map<Integer, Contact> contacts = user.getContacts();
        contacts.put(contact.getId(), contact);
        user.setContacts(contacts);
        return contact;

    }

    @Override
    public Map<Integer, Contact> findContacts(int id) {
         return UserStorage.getById(id).getContacts();
    }

    @Override
    public Contact updateContact(int userId, int id, Contact contact) {
        User user = UserStorage.getById(userId);
        user.getContacts().remove(id);
        return user.getContacts().put(id , contact);
    }
}
