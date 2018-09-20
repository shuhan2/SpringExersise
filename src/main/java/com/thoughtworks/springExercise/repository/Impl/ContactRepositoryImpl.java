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
        ContactStorage.add(contact);
        user.getContacts().put(contact.getId(), contact);
        return contact;
    }

    @Override
    public Map<Integer, Contact> findContacts(int id) {

        return UserStorage.getById(id).getContacts();
    }

    @Override
    public Contact updateContact(int userId, int id, Contact contact) {
        UserStorage.getById(userId).getContacts().remove(id);
        return UserStorage.getById(userId).getContacts().put(id , contact);
    }

    @Override
    public void deleteContact(int userId, int contactId) {
        UserStorage.getById(userId).getContacts().remove(contactId);
    }

    @Override
    public Contact findContactByName(String userName, String contactName) {
        User user = UserStorage.getByName(userName);
        if (user == null) {
            return null;
        }
        Map<Integer, Contact> contacts = user.getContacts();
        return ContactStorage.getContactByName(contacts, contactName);
    }
}
