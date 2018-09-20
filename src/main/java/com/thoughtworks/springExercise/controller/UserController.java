package com.thoughtworks.springExercise.controller;

import com.thoughtworks.springExercise.domain.Contact;
import com.thoughtworks.springExercise.domain.User;
import com.thoughtworks.springExercise.repository.Impl.ContactRepositoryImpl;
import com.thoughtworks.springExercise.repository.Impl.UserRepositoryImpl;
import com.thoughtworks.springExercise.repository.Impl.UserStorage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class UserController {
    @RequestMapping("/users")
    public Collection<User> findUsers(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        return userRepository.findUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        UserStorage.add(user);
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        return new ResponseEntity<>(userRepository.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        return new ResponseEntity<>(userRepository.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.delete(id);
    }
    @PostMapping("/users/{userId}/contacts")
    public ResponseEntity<Contact> createContact(@PathVariable int userId, @RequestBody Contact contact){
        ContactRepositoryImpl contactRepository = new ContactRepositoryImpl();
        return new ResponseEntity<>(contactRepository.createContactForUser(userId , contact), HttpStatus.CREATED);
    }
    @RequestMapping("/users/{userId}/contacts")
    public ResponseEntity<Map<Integer, Contact>> getContacts(@PathVariable int userId ) {
        ContactRepositoryImpl contactRepository = new ContactRepositoryImpl();
        return new ResponseEntity<>(contactRepository.findContacts(userId), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/contacts/{contactId}")
    public ResponseEntity<Contact> updateContact(@PathVariable int userId, @PathVariable int contactId, @RequestBody Contact contact ) {
        ContactRepositoryImpl contactRepository = new ContactRepositoryImpl();
        return new ResponseEntity<>(contactRepository.updateContact(userId, contactId, contact), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int userId, @PathVariable int contactId) {
        ContactRepositoryImpl contactRepository = new ContactRepositoryImpl();
        contactRepository.deleteContact(userId, contactId);
    }

    @RequestMapping("/users/{userName}/contacts/{contactName}")
    public ResponseEntity<Contact> findContactByName(@PathVariable String userName, @PathVariable String contactName) {
        ContactRepositoryImpl contactRepository = new ContactRepositoryImpl();
        Contact contact = contactRepository.findContactByName(userName, contactName);
        if (contact == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }





}
